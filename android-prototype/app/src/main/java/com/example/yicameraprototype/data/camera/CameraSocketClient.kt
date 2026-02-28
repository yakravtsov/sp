package com.example.yicameraprototype.data.camera

import android.net.Network
import android.util.Log
import com.example.yicameraprototype.domain.CameraMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStreamReader
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException
import java.util.concurrent.LinkedBlockingQueue

class CameraSocketClient(
    private val host: String = "192.168.42.1",
    private val port: Int = 7878,
    private val json: Json = Json { ignoreUnknownKeys = true; encodeDefaults = false }
) {
    companion object {
        private const val TAG = "CameraSocket"
        private const val CONNECT_TIMEOUT_MS = 2000
        private const val SO_TIMEOUT_MS = 2000
        private const val TRAFFIC_CLASS = 20
        private const val READ_BUFFER_SIZE = 32768
        private const val MAX_CONNECT_RETRIES = 3
        private const val RETRY_DELAY_MS = 1000L
        private val ALLOW_DUPLICATES = setOf(515, 16777241)
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val writeMutex = Mutex()

    private val _incoming = MutableSharedFlow<CameraMessage>(extraBufferCapacity = 256)
    private val _connected = MutableStateFlow(false)

    val incoming: SharedFlow<CameraMessage> = _incoming.asSharedFlow()
    val connected: StateFlow<Boolean> = _connected.asStateFlow()

    var onMessageReceived: ((CameraMessage) -> Unit)? = null

    private var socket: Socket? = null
    private val commandQueue = LinkedBlockingQueue<CameraMessage>()
    @Volatile private var canSendNext = true

    suspend fun connect(network: Network? = null, retries: Int = MAX_CONNECT_RETRIES): Result<Unit> = withContext(Dispatchers.IO) {
        var last: Throwable? = null
        repeat(retries) { attempt ->
            val result = runCatching {
                val sock = if (network != null) {
                    network.socketFactory.createSocket()
                } else {
                    Socket()
                }
                sock.apply {
                    tcpNoDelay = true
                    trafficClass = TRAFFIC_CLASS
                    keepAlive = true
                    soTimeout = SO_TIMEOUT_MS
                    reuseAddress = true
                }
                sock.connect(InetSocketAddress(host, port), CONNECT_TIMEOUT_MS)
                socket = sock
                canSendNext = true
                _connected.value = true
                Log.d(TAG, "connect: established on attempt ${attempt + 1}")
                startReadLoop()
                startWriteLoop()
            }
            if (result.isSuccess) return@withContext Result.success(Unit)
            last = result.exceptionOrNull()
            Log.w(TAG, "connect: attempt ${attempt + 1} failed: ${last?.message}")
            close()
            if (attempt < retries - 1) Thread.sleep(RETRY_DELAY_MS)
        }
        Result.failure(last ?: IllegalStateException("TCP connect failed"))
    }

    fun enqueue(message: CameraMessage) {
        if (!ALLOW_DUPLICATES.contains(message.msgId)) {
            commandQueue.removeAll { it.msgId == message.msgId }
        }
        commandQueue.put(message)
        Log.d(TAG, "enqueue: msgId=${message.msgId}, queueSize=${commandQueue.size}")
    }

    fun sendPriority(message: CameraMessage) {
        scope.launch(Dispatchers.IO) {
            writeMutex.withLock {
                writeRaw(message)
            }
        }
    }

    fun notifyCanSendNext() {
        canSendNext = true
    }

    fun close() {
        _connected.value = false
        runCatching { socket?.close() }
        socket = null
    }

    fun drainQueue() {
        commandQueue.clear()
    }

    private fun writeRaw(message: CameraMessage) {
        val payload = json.encodeToString<CameraMessage>(message)
        Log.d(TAG, "writeRaw: $payload")
        socket?.getOutputStream()?.let { out ->
            out.write(payload.toByteArray(Charsets.UTF_8))
            out.flush()
        }
    }

    private fun startWriteLoop() {
        scope.launch(Dispatchers.IO) {
            Log.d(TAG, "writeLoop: started")
            try {
                while (_connected.value) {
                    if (!canSendNext) {
                        Thread.sleep(10)
                        continue
                    }
                    val command = commandQueue.poll(100, java.util.concurrent.TimeUnit.MILLISECONDS)
                        ?: continue
                    if (!_connected.value) break
                    canSendNext = false
                    writeMutex.withLock {
                        runCatching { writeRaw(command) }.onFailure { e ->
                            Log.e(TAG, "writeLoop: send failed msgId=${command.msgId}", e)
                            _connected.value = false
                        }
                    }
                }
            } catch (e: InterruptedException) {
                Log.d(TAG, "writeLoop: interrupted")
            }
            Log.d(TAG, "writeLoop: exited")
        }
    }

    private fun startReadLoop() {
        scope.launch(Dispatchers.IO) {
            val input = socket?.getInputStream() ?: return@launch
            val reader = InputStreamReader(input, Charsets.UTF_8)
            val buf = CharArray(READ_BUFFER_SIZE)
            val frameBuffer = StringBuilder()
            Log.d(TAG, "readLoop: started")
            while (_connected.value) {
                val read: Int
                try {
                    read = reader.read(buf)
                } catch (_: SocketTimeoutException) {
                    continue
                } catch (e: Exception) {
                    Log.e(TAG, "readLoop: IOException, breaking", e)
                    break
                }
                if (read < 0) {
                    Log.e(TAG, "readLoop: EOF, breaking")
                    break
                }
                if (read == 0) continue
                frameBuffer.append(buf, 0, read)
                Log.d(TAG, "readLoop: received $read chars")
                extractJsonFrames(frameBuffer).forEach { raw ->
                    if (!raw.contains("\"msg_id\"")) return@forEach
                    Log.d(TAG, "readLoop: JSON: $raw")
                    runCatching { json.decodeFromString<CameraMessage>(raw) }.onSuccess { msg ->
                        onMessageReceived?.invoke(msg)
                        _incoming.tryEmit(msg)
                    }.onFailure { e ->
                        Log.e(TAG, "readLoop: decode failed: $raw", e)
                    }
                }
            }
            Log.e(TAG, "readLoop: exited")
            _connected.value = false
        }
    }

    private fun extractJsonFrames(buffer: StringBuilder): List<String> {
        val out = mutableListOf<String>()
        var depth = 0
        var start = -1
        var inString = false
        var escape = false
        var consumed = 0

        for (i in 0 until buffer.length) {
            val c = buffer[i]
            if (inString) {
                if (escape) {
                    escape = false
                } else if (c == '\\') {
                    escape = true
                } else if (c == '"') {
                    inString = false
                }
                continue
            }
            if (c == '"') {
                inString = true
                continue
            }
            if (c == '{') {
                if (depth == 0) start = i
                depth++
            } else if (c == '}') {
                if (depth > 0) depth--
                if (depth == 0 && start >= 0) {
                    out += buffer.substring(start, i + 1)
                    consumed = i + 1
                    start = -1
                }
            }
        }

        if (consumed > 0) buffer.delete(0, consumed)
        return out
    }
}

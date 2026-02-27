package com.example.yicameraprototype.data.camera

import com.example.yicameraprototype.domain.CameraMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
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
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.atomic.AtomicInteger

class CameraSocketClient(
    private val host: String = "192.168.42.1",
    private val port: Int = 7878,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val commandQueue = Channel<CameraMessage>(capacity = Channel.UNLIMITED)
    private val writeMutex = Mutex()
    private val _incoming = MutableSharedFlow<CameraMessage>(extraBufferCapacity = 256)
    private val _connected = MutableStateFlow(false)

    val incoming: SharedFlow<CameraMessage> = _incoming.asSharedFlow()
    val connected: StateFlow<Boolean> = _connected.asStateFlow()

    private var socket: Socket? = null
    private val sequence = AtomicInteger(0)

    suspend fun connect(retries: Int = 3): Result<Unit> = withContext(Dispatchers.IO) {
        var last: Throwable? = null
        repeat(retries) {
            val result = runCatching {
                socket = Socket().apply {
                    keepAlive = true
                    tcpNoDelay = true
                    soTimeout = 2000
                    connect(InetSocketAddress(host, port), 2000)
                }
                _connected.value = true
                startReadLoop()
                startWriteLoop()
            }
            if (result.isSuccess) return@withContext Result.success(Unit)
            last = result.exceptionOrNull()
            close()
        }
        Result.failure(last ?: IllegalStateException("TCP connect failed"))
    }

    fun nextSequence(): Int = sequence.incrementAndGet()

    suspend fun send(message: CameraMessage) {
        commandQueue.send(if (message.seq == null) message.copy(seq = nextSequence()) else message)
    }

    suspend fun sendRaw(message: CameraMessage) {
        writeMutex.withLock {
            val payload = json.encodeToString(message)
            socket?.getOutputStream()?.write((payload + "\n").toByteArray())
            socket?.getOutputStream()?.flush()
        }
    }

    fun close() {
        _connected.value = false
        runCatching { socket?.close() }
        socket = null
    }

    private fun startWriteLoop() {
        scope.launch {
            for (command in commandQueue) {
                if (!_connected.value) break
                runCatching { sendRaw(command) }.onFailure {
                    _connected.value = false
                }
            }
        }
    }

    private fun startReadLoop() {
        scope.launch {
            val input = socket?.getInputStream() ?: return@launch
            val buf = ByteArray(4096)
            val frameBuffer = StringBuilder()
            while (_connected.value) {
                val read = runCatching { input.read(buf) }.getOrElse { -1 }
                if (read <= 0) break
                frameBuffer.append(String(buf, 0, read))
                extractJsonFrames(frameBuffer).forEach { raw ->
                    runCatching { json.decodeFromString<CameraMessage>(raw) }.onSuccess {
                        _incoming.tryEmit(it)
                    }
                }
            }
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

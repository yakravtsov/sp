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
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.InetSocketAddress
import java.net.Socket

class CameraSocketClient(
    private val host: String = "192.168.42.1",
    private val port: Int = 7878,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val commandQueue = Channel<CameraMessage>(capacity = Channel.UNLIMITED)
    private val _incoming = MutableSharedFlow<CameraMessage>(extraBufferCapacity = 64)
    private val _connected = MutableStateFlow(false)
    val incoming: SharedFlow<CameraMessage> = _incoming.asSharedFlow()
    val connected: StateFlow<Boolean> = _connected.asStateFlow()

    private var socket: Socket? = null
    private var writer: PrintWriter? = null

    suspend fun connect(retries: Int = 3): Result<Unit> = withContext(Dispatchers.IO) {
        repeat(retries) {
            runCatching {
                socket = Socket().apply {
                    keepAlive = true
                    tcpNoDelay = true
                    soTimeout = 2000
                    connect(InetSocketAddress(host, port), 2000)
                }
                writer = PrintWriter(socket!!.getOutputStream(), true)
                _connected.value = true
                startReadLoop()
                startWriteLoop()
                return@withContext Result.success(Unit)
            }
        }
        Result.failure(IllegalStateException("Failed to connect to camera TCP socket"))
    }

    suspend fun send(message: CameraMessage) {
        commandQueue.send(message)
    }

    fun close() {
        _connected.value = false
        runCatching { socket?.close() }
        socket = null
        writer = null
    }

    private fun startWriteLoop() {
        scope.launch {
            for (command in commandQueue) {
                val line = json.encodeToString(command)
                writer?.println(line)
            }
        }
    }

    private fun startReadLoop() {
        scope.launch {
            val localSocket = socket ?: return@launch
            val reader = BufferedReader(InputStreamReader(localSocket.getInputStream()))
            val buffer = StringBuilder()
            while (_connected.value) {
                val chunk = reader.readLine() ?: break
                buffer.append(chunk)
                parseChunkedJson(buffer).forEach { _incoming.emit(it) }
            }
            _connected.value = false
        }
    }

    private fun parseChunkedJson(buffer: StringBuilder): List<CameraMessage> {
        val messages = mutableListOf<CameraMessage>()
        var depth = 0
        var start = -1
        var consumedUntil = 0

        buffer.forEachIndexed { i, c ->
            if (c == '{') {
                if (depth == 0) start = i
                depth++
            }
            if (c == '}') {
                depth--
                if (depth == 0 && start >= 0) {
                    val raw = buffer.substring(start, i + 1)
                    runCatching { json.decodeFromString<CameraMessage>(raw) }
                        .onSuccess { messages += it }
                    consumedUntil = i + 1
                    start = -1
                }
            }
        }

        if (consumedUntil > 0) buffer.delete(0, consumedUntil)
        return messages
    }
}

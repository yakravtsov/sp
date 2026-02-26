package com.example.yicameraprototype.data.camera

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.example.yicameraprototype.data.network.CameraNetworkBinder
import com.example.yicameraprototype.domain.CameraFile
import com.example.yicameraprototype.domain.CameraMessage
import com.example.yicameraprototype.domain.CameraSetting
import com.example.yicameraprototype.domain.ConnectionState
import com.example.yicameraprototype.domain.LiveState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

class CameraRepository(private val context: Context) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val binder = CameraNetworkBinder(context)
    private val socketClient = CameraSocketClient()
    private val sessionManager = SessionManager(socketClient)
    private val httpClient = OkHttpClient()

    private val _uiState = MutableStateFlow(com.example.yicameraprototype.domain.CameraUiState())
    val uiState: StateFlow<com.example.yicameraprototype.domain.CameraUiState> = _uiState.asStateFlow()

    suspend fun connect() {
        log("Connect started")
        _uiState.update { it.copy(connectionState = ConnectionState.Connecting) }

        val bound = binder.bindCameraNetwork()
        if (bound.isFailure || !binder.isCameraReachable()) {
            setError("Camera network unavailable")
            return
        }

        if (socketClient.connect().isFailure) {
            setError("TCP connect failed")
            return
        }
        _uiState.update { it.copy(connectionState = ConnectionState.Connected) }

        val tokenResult = sessionManager.startSession()
        if (tokenResult.isFailure) {
            setError("Start session failed")
            return
        }

        _uiState.update {
            it.copy(connectionState = ConnectionState.SessionActive, token = tokenResult.getOrNull())
        }
        sessionManager.initializeCameraForJ22()
        _uiState.update { it.copy(connectionState = ConnectionState.CameraReady) }

        observeIncoming()
        refreshSettings()
        refreshFiles()
    }

    suspend fun disconnect() {
        sessionManager.stopSession()
        socketClient.close()
        binder.unbind()
        _uiState.update { com.example.yicameraprototype.domain.CameraUiState() }
        log("Disconnected")
    }

    suspend fun changeSetting(key: String, value: String) {
        val token = uiState.value.token ?: return
        socketClient.send(CameraMessage(msgId = 2, token = token, param = JsonObject(mapOf(key to JsonPrimitive(value)))))
        if (key == "warp_enable") {
            socketClient.send(CameraMessage(msgId = 259, token = token, param = JsonPrimitive("none_force")))
        }
        refreshSettings()
    }

    suspend fun startRecord() = sendSimple(513)
    suspend fun stopRecord() = sendSimple(514)
    suspend fun takePhoto() = sendSimple(16777220)

    suspend fun refreshSettings() {
        val token = uiState.value.token ?: return
        socketClient.send(CameraMessage(msgId = 3, token = token))
        socketClient.send(CameraMessage(msgId = 9, token = token))
        _uiState.update {
            it.copy(
                settings = listOf(
                    CameraSetting("video_resolution", "1920x1080", listOf("1920x1080", "2.7K", "4K")),
                    CameraSetting("video_quality", "high", listOf("high", "medium", "low")),
                    CameraSetting("warp_enable", "on", listOf("on", "off"))
                )
            )
        }
    }

    suspend fun refreshFiles() {
        val token = uiState.value.token ?: return
        socketClient.send(
            CameraMessage(
                msgId = 1282,
                token = token,
                param = JsonObject(mapOf("path" to JsonPrimitive("/tmp/fuse_d/DCIM"), "from" to JsonPrimitive(0), "to" to JsonPrimitive(100)))
            )
        )
    }

    suspend fun download(file: CameraFile) {
        val request = Request.Builder().url("http://192.168.42.1/DCIM/${file.name}").build()
        val localFile = File(context.filesDir, file.name)
        _uiState.update { it.copy(downloading = it.downloading + (file.name to 0)) }

        httpClient.newCall(request).execute().use { response ->
            val body = response.body ?: return
            val total = body.contentLength().coerceAtLeast(1)
            body.byteStream().use { input ->
                localFile.outputStream().use { output ->
                    val buf = ByteArray(8192)
                    var read = input.read(buf)
                    var copied = 0L
                    while (read > 0) {
                        output.write(buf, 0, read)
                        copied += read
                        _uiState.update {
                            it.copy(downloading = it.downloading + (file.name to ((copied * 100 / total).toInt())))
                        }
                        read = input.read(buf)
                    }
                }
            }
        }
        log("Downloaded ${file.name} -> ${localFile.absolutePath}")
    }

    fun share(file: CameraFile): Intent {
        val localFile = File(context.filesDir, file.name)
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", localFile)
        return Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }

    fun onLiveStateChanged(state: LiveState, reason: String? = null) {
        _uiState.update { it.copy(liveState = state) }
        log("Live: $state ${reason.orEmpty()}")
    }

    private suspend fun sendSimple(msgId: Int) {
        val token = uiState.value.token ?: return
        socketClient.send(CameraMessage(msgId = msgId, token = token))
    }

    private fun observeIncoming() {
        scope.launch {
            socketClient.incoming.collect { msg ->
                when (msg.msgId) {
                    7 -> log("Notify(7): ${msg.param}")
                    13 -> log("Battery: ${msg.param}")
                    1793 -> socketClient.send(CameraMessage(msgId = 1793, token = uiState.value.token ?: 0))
                    1282 -> {
                        val files = parseFiles(msg.param)
                        _uiState.update { it.copy(files = files) }
                    }
                }
            }
        }
    }

    private fun parseFiles(param: kotlinx.serialization.json.JsonElement?): List<CameraFile> {
        val arr = (param as? JsonArray) ?: return emptyList()
        return arr.mapNotNull {
            val o = it as? JsonObject ?: return@mapNotNull null
            CameraFile(
                path = o["path"]?.toString()?.trim('"') ?: return@mapNotNull null,
                name = o["name"]?.toString()?.trim('"') ?: return@mapNotNull null,
                size = o["size"]?.toString()?.trim('"')?.toLongOrNull() ?: 0,
                type = o["type"]?.toString()?.trim('"') ?: "unknown",
                date = o["date"]?.toString()?.trim('"')
            )
        }
    }

    private fun setError(message: String) {
        _uiState.update { it.copy(connectionState = ConnectionState.Error, error = message) }
        log("ERROR: $message")
    }

    private fun log(message: String) {
        _uiState.update { state ->
            state.copy(logs = (state.logs + message).takeLast(250))
        }
    }
}

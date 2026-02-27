package com.example.yicameraprototype.data.camera

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.StatFs
import androidx.core.content.FileProvider
import com.example.yicameraprototype.data.network.CameraNetworkBinder
import com.example.yicameraprototype.domain.CameraFile
import com.example.yicameraprototype.domain.CameraMessage
import com.example.yicameraprototype.domain.CameraSetting
import com.example.yicameraprototype.domain.CameraUiState
import com.example.yicameraprototype.domain.ConnectionState
import com.example.yicameraprototype.domain.DebugLogEntry
import com.example.yicameraprototype.domain.LiveState
import com.example.yicameraprototype.domain.SessionFlags
import com.example.yicameraprototype.domain.Severity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.contentOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CameraRepository(private val context: Context) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val binder = CameraNetworkBinder(context)
    private val socketClient = CameraSocketClient()
    private val sessionManager = SessionManager(socketClient)
    private val httpClient = OkHttpClient()

    private val _uiState = MutableStateFlow(CameraUiState())
    val uiState: StateFlow<CameraUiState> = _uiState.asStateFlow()

    private val optionsCache = mutableMapOf<String, List<String>>()
    private val inFlightMutex = Mutex()
    private var autoStartLiveAfterReconnect = false
    private var isManualDisconnect = false
    private var reconnectInProgress = false
    private var recordPollJob: Job? = null

    init {
        observeIncoming()
        observeSocketConnectivity()
    }

    suspend fun connect() {
        isManualDisconnect = false
        transition(ConnectionState.BindingNetwork)
        val network = binder.bindCameraNetwork()
        if (network.isFailure || !binder.isCameraReachable()) {
            return setError("Camera network unavailable")
        }

        transition(ConnectionState.ConnectingTcp)
        if (socketClient.connect().isFailure) {
            return setError("TCP connect failed")
        }
        log("CameraSocket", Severity.Info, "TCP connected")

        transition(ConnectionState.SessionStarting)
        val tokenResult = sessionManager.startSession()
        if (tokenResult.isFailure) {
            return setError("Start session failed")
        }

        _uiState.update {
            it.copy(
                token = tokenResult.getOrNull(),
                sessionFlags = sessionManager.flags.value,
                connectionState = ConnectionState.SessionActive,
                error = null
            )
        }

        transition(ConnectionState.Initializing)
        sessionManager.initializeCameraForJ22()
        refreshSettings()
        refreshFiles(0, 60)
        transition(ConnectionState.CameraReady)
        if (autoStartLiveAfterReconnect) {
            onLiveStateChanged(LiveState.Buffering, "auto-reconnect")
        }
    }

    suspend fun disconnect() {
        isManualDisconnect = true
        stopRecordPolling()
        autoStartLiveAfterReconnect = false
        onLiveStateChanged(LiveState.Stopped, "manual disconnect")
        sessionManager.stopSession()
        socketClient.close()
        binder.unbind()
        _uiState.update { CameraUiState() }
        log("CameraRepository", Severity.Info, "Disconnected")
    }

    suspend fun reconnect(reason: String) {
        if (reconnectInProgress) return
        reconnectInProgress = true
        transition(ConnectionState.Reconnecting)
        log("CameraRepository", Severity.Warn, "Reconnect requested: $reason")
        autoStartLiveAfterReconnect = uiState.value.liveState != LiveState.Stopped
        runCatching { disconnect() }
        isManualDisconnect = false
        runCatching { connect() }
        reconnectInProgress = false
    }

    suspend fun changeSetting(key: String, value: String) {
        val token = uiState.value.token ?: return
        val request = CameraMessage(
            msgId = 2,
            token = token,
            param = JsonObject(mapOf(key to JsonPrimitive(value)))
        )
        val response = sendAwaitSingleFlight(request)
        val rval = response?.rval ?: 0
        if (!handleRval(rval, "set $key")) return

        if (key == "warp_enable") {
            sendAwaitSingleFlight(CameraMessage(msgId = 259, token = token, param = JsonPrimitive("none_force")))
        }
        val related = extractRelated(response?.param)
        if (related > 0) refreshSettings()
        refreshSettings()
    }

    suspend fun startRecord() {
        if (uiState.value.isRecording) {
            log("CameraRepository", Severity.Warn, "Start record skipped: already recording")
            return
        }
        sendSimple(513)
        startRecordPollingIfNeeded()
    }

    suspend fun stopRecord() {
        if (!uiState.value.isRecording) {
            log("CameraRepository", Severity.Warn, "Stop record skipped: not recording")
            return
        }
        sendSimple(514)
        stopRecordPolling()
        refreshFiles(uiState.value.fileWindowFrom, uiState.value.fileWindowTo)
    }

    suspend fun takePhoto() {
        sendSimple(16777220)
        refreshFiles(uiState.value.fileWindowFrom, uiState.value.fileWindowTo)
    }

    suspend fun pollRecordStatus() {
        if (!uiState.value.isRecording) return
        sendSimple(16777241)
    }

    suspend fun refreshSettings() {
        val token = uiState.value.token ?: return
        val allSettingsRequest = CameraMessage(msgId = 3, token = token)
        val optionsRequest = CameraMessage(msgId = 9, token = token)

        val settingsResponse = sendAwaitSingleFlight(allSettingsRequest)
        val optionsResponse = sendAwaitSingleFlight(optionsRequest)

        optionsCache.clear()
        optionsCache.putAll(parseOptions(optionsResponse?.param))

        _uiState.update { it.copy(settings = parseSettings(settingsResponse?.param, optionsCache)) }
    }

    suspend fun refreshFiles(from: Int, to: Int) {
        val token = uiState.value.token ?: return
        _uiState.update { it.copy(fileWindowFrom = from, fileWindowTo = to) }
        val request = CameraMessage(
            msgId = 1282,
            token = token,
            param = JsonObject(mapOf("path" to JsonPrimitive("/tmp/fuse_d/DCIM"), "from" to JsonPrimitive(from), "to" to JsonPrimitive(to)))
        )
        sendAwaitSingleFlight(request)
    }

    suspend fun download(file: CameraFile) {
        val target = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), file.name)
        if (!hasEnoughSpace(file.size, target.parentFile ?: context.filesDir)) {
            setError("No free space for ${file.name}")
            return
        }

        val request = Request.Builder().url("http://192.168.42.1/DCIM/${file.name}").build()
        _uiState.update { it.copy(downloading = it.downloading + (file.name to 0)) }

        runCatching {
            httpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) error("HTTP ${response.code}")
                val body = response.body ?: error("empty body")
                val total = body.contentLength().coerceAtLeast(1)

                body.byteStream().use { input ->
                    target.outputStream().use { output ->
                        val buf = ByteArray(8192)
                        var copied = 0L
                        while (true) {
                            val read = input.read(buf)
                            if (read <= 0) break
                            output.write(buf, 0, read)
                            copied += read
                            _uiState.update {
                                it.copy(downloading = it.downloading + (file.name to ((copied * 100 / total).toInt())))
                            }
                        }
                    }
                }
            }
        }.onFailure {
            _uiState.update { state -> state.copy(downloading = state.downloading - file.name) }
            setError("Download failed: ${it.message}")
            return
        }

        _uiState.update {
            it.copy(
                downloading = it.downloading - file.name,
                files = it.files.map { current ->
                    if (current.name == file.name) current.copy(downloaded = true) else current
                }
            )
        }
        log("CameraRepository", Severity.Info, "Downloaded ${file.name} -> ${target.absolutePath}")
    }

    fun open(file: CameraFile): Intent {
        val localFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), file.name)
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", localFile)
        return Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "*/*")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }

    fun share(file: CameraFile): Intent {
        val localFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), file.name)
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", localFile)
        return Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }

    fun exportDebugLog(): Uri {
        val output = File(context.cacheDir, "camera-debug-${SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(Date())}.txt")
        output.writeText(
            uiState.value.logs.joinToString("\n") { entry ->
                "${entry.ts}|${entry.tag}|${entry.severity}|${entry.message}"
            }
        )
        return FileProvider.getUriForFile(context, "${context.packageName}.provider", output)
    }

    fun onLiveStateChanged(state: LiveState, reason: String? = null) {
        _uiState.update { it.copy(liveState = state) }
        log("Live", Severity.Info, "state=$state reason=${reason.orEmpty()}")
    }

    private suspend fun sendSimple(msgId: Int) {
        val token = uiState.value.token ?: return
        val request = CameraMessage(msgId = msgId, token = token)
        val response = sendAwaitSingleFlight(request)
        handleRval(response?.rval ?: 0, "msg_id=$msgId")
    }

    private suspend fun sendAwaitSingleFlight(request: CameraMessage): CameraMessage? {
        return inFlightMutex.withLock {
            sessionManager.sendAndAwait(request).getOrNull()
        }
    }

    private fun observeIncoming() {
        scope.launch {
            socketClient.incoming.collect { msg ->
                when (msg.msgId) {
                    7 -> {
                        applyNotify(msg.param)
                        log("CameraRepository", Severity.Info, "notify ${msg.param}")
                    }
                    13 -> {
                        parseBattery(msg.param)
                    }
                    1793 -> {
                        sessionManager.handleIncomingHeartbeat(msg)
                        log("SessionManager", Severity.Debug, "incoming heartbeat acked")
                    }
                    1282 -> {
                        _uiState.update { it.copy(files = parseFiles(msg.param)) }
                    }
                }
                if (msg.rval != null) handleRval(msg.rval, "incoming msg_id=${msg.msgId}")
                if (msg.msgId == 257) {
                    _uiState.update { it.copy(sessionFlags = sessionManager.flags.value) }
                    validateSessionFlags(sessionManager.flags.value)
                }
            }
        }
    }

    private fun observeSocketConnectivity() {
        scope.launch {
            socketClient.connected
                .drop(1)
                .distinctUntilChanged()
                .collect { connected ->
                    if (connected) {
                        log("CameraSocket", Severity.Debug, "socket connected=true")
                    } else {
                        log("CameraSocket", Severity.Warn, "socket connected=false")
                        val hasSession = uiState.value.token != null
                        if (!isManualDisconnect && hasSession) {
                            reconnect("tcp disconnected")
                        }
                    }
                }
        }
    }


    private fun extractRelated(param: JsonElement?): Int {
        val p = param as? JsonObject ?: return 0
        return (p["related"] as? JsonPrimitive)?.intOrNull ?: 0
    }

    private fun parseSettings(param: JsonElement?, optionsByKey: Map<String, List<String>>): List<CameraSetting> {
        val obj = param as? JsonObject ?: return emptyList()
        val supported = listOf("video_resolution", "video_quality", "video_standard", "warp_enable", "photo_size", "photo_quality")
        return supported.mapNotNull { key ->
            val value = (obj[key] as? JsonPrimitive)?.contentOrNull ?: return@mapNotNull null
            CameraSetting(
                key = key,
                value = value,
                options = optionsByKey[key].orEmpty(),
                related = (obj["related"] as? JsonPrimitive)?.intOrNull ?: 0
            )
        }
    }

    private fun parseOptions(param: JsonElement?): Map<String, List<String>> {
        val root = param as? JsonObject ?: return emptyMap()
        return root.mapValues { (_, value) ->
            (value as? JsonArray)?.mapNotNull { (it as? JsonPrimitive)?.contentOrNull } ?: emptyList()
        }
    }

    private fun parseFiles(param: JsonElement?): List<CameraFile> {
        val obj = param as? JsonObject ?: return emptyList()
        val list = (obj["listing"] as? JsonArray) ?: (obj["files"] as? JsonArray) ?: return emptyList()
        return list.mapNotNull {
            val o = it as? JsonObject ?: return@mapNotNull null
            val name = (o["name"] as? JsonPrimitive)?.contentOrNull ?: return@mapNotNull null
            CameraFile(
                path = (o["path"] as? JsonPrimitive)?.contentOrNull ?: "/tmp/fuse_d/DCIM/$name",
                name = name,
                size = (o["size"] as? JsonPrimitive)?.contentOrNull?.toLongOrNull() ?: 0L,
                type = (o["type"] as? JsonPrimitive)?.contentOrNull ?: inferType(name),
                date = (o["date"] as? JsonPrimitive)?.contentOrNull
            )
        }
    }

    private fun inferType(name: String): String = when {
        name.endsWith(".mp4", true) -> "video"
        name.endsWith(".jpg", true) || name.endsWith(".jpeg", true) -> "photo"
        else -> "unknown"
    }

    private fun parseBattery(param: JsonElement?) {
        val p = param as? JsonObject ?: return
        _uiState.update {
            it.copy(
                batteryPercent = (p["battery"] as? JsonPrimitive)?.contentOrNull?.toIntOrNull(),
                adapterConnected = (p["adapter_status"] as? JsonPrimitive)?.contentOrNull == "1"
            )
        }
    }

    private fun applyNotify(param: JsonElement?) {
        val p = param as? JsonObject ?: return
        val recording = (p["record_status"] as? JsonPrimitive)?.contentOrNull == "recording"
        _uiState.update {
            it.copy(
                isRecording = recording,
                sdCardStatus = (p["sd_card_status"] as? JsonPrimitive)?.contentOrNull ?: it.sdCardStatus,
                appStatus = (p["app_status"] as? JsonPrimitive)?.contentOrNull ?: it.appStatus
            )
        }
        if (recording) startRecordPollingIfNeeded() else stopRecordPolling()
    }

    private fun startRecordPollingIfNeeded() {
        if (recordPollJob?.isActive == true) return
        recordPollJob = scope.launch {
            while (uiState.value.isRecording) {
                sendSimple(16777241)
                delay(1200)
            }
        }
    }

    private fun stopRecordPolling() {
        recordPollJob?.cancel()
        recordPollJob = null
    }

    private fun validateSessionFlags(flags: SessionFlags) {
        if (!flags.live) {
            log("SessionManager", Severity.Warn, "Session flag live=false")
        }
    }

    private suspend fun handleRval(rval: Int, context: String): Boolean {
        return when (rval) {
            0 -> true
            -21 -> {
                log("CameraRepository", Severity.Warn, "$context busy (-21), retry in 400ms")
                delay(400)
                true
            }
            -4 -> {
                log("SessionManager", Severity.Warn, "$context token invalid (-4), reconnect")
                reconnect("token invalid")
                false
            }
            -3 -> {
                setError("$context invalid parameter (-3)")
                false
            }
            else -> {
                setError("$context failed: rval=$rval")
                false
            }
        }
    }

    private fun hasEnoughSpace(requiredBytes: Long, atDir: File): Boolean {
        val stat = StatFs(atDir.absolutePath)
        val freeBytes = stat.availableBytes
        return freeBytes > requiredBytes + 1_000_000
    }

    private fun transition(next: ConnectionState) {
        _uiState.update { it.copy(connectionState = next, error = null) }
        log("CameraRepository", Severity.Info, "state->$next")
        if (next == ConnectionState.Error || next == ConnectionState.Reconnecting) {
            onLiveStateChanged(LiveState.Error, "connection state $next")
        }
    }

    private fun setError(message: String) {
        _uiState.update { it.copy(connectionState = ConnectionState.Error, error = message) }
        onLiveStateChanged(LiveState.Error, message)
        log("CameraRepository", Severity.Error, message)
    }

    private fun log(tag: String, severity: Severity, message: String) {
        _uiState.update {
            it.copy(logs = (it.logs + DebugLogEntry(System.currentTimeMillis(), tag, severity, message)).takeLast(400))
        }
    }
}

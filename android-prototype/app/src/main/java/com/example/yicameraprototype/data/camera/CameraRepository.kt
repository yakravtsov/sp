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
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.withTimeoutOrNull
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
import java.util.concurrent.ConcurrentHashMap

class CameraRepository(private val context: Context) {
    companion object {
        private const val TAG = "CameraRepository"
        private val OPTIONS_TO_QUERY = listOf(
            "video_resolution", "photo_size", "video_standard", "video_quality",
            "video_stamp", "photo_stamp", "photo_quality",
            "meter_mode", "led_mode", "buzzer_volume",
            "auto_low_light", "auto_power_off", "video_rotate",
            "video_output_dev_type", "capture_default_mode", "system_default_mode",
            "precise_selftime", "burst_capture_number", "precise_cont_time",
            "timelapse_video", "timelapse_video_duration", "timelapse_video_resolution",
            "record_photo_time", "rc_button_mode", "osd_enable", "warp_enable",
            "rec_default_mode"
        )
        private val DISPLAYED_SETTINGS = listOf(
            "video_resolution", "video_quality", "video_standard", "warp_enable",
            "photo_size", "photo_quality", "capture_default_mode", "rec_default_mode",
            "system_default_mode", "auto_low_light", "video_rotate", "osd_enable",
            "buzzer_volume", "led_mode", "auto_power_off", "meter_mode"
        )
        private const val RECORD_POLL_INTERVAL_MS = 800L
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val binder = CameraNetworkBinder(context)
    private val socketClient = CameraSocketClient()
    private val sessionManager = SessionManager(socketClient)

    init {
        socketClient.onMessageReceived = null
    }

    var httpClient = OkHttpClient()
        private set
    @Volatile private var cameraNetwork: android.net.Network? = null

    private val _uiState = MutableStateFlow(CameraUiState())
    val uiState: StateFlow<CameraUiState> = _uiState.asStateFlow()

    private val settingsCache = ConcurrentHashMap<String, String>()
    private val optionsCache = ConcurrentHashMap<String, List<String>>()
    private val inFlightMutex = Mutex()
    private var autoStartLiveAfterReconnect = false
    @Volatile var isManualDisconnect = false
        private set
    private var reconnectInProgress = false
    private var connectInProgress = false
    private var reconnectCount = 0
    private val maxReconnectAttempts = 3
    private var recordPollJob: Job? = null
    @Volatile private var albumMode = false
    @Volatile private var vfStopSignal: CompletableDeferred<Unit>? = null

    init {
        observeIncoming()
        observeSocketConnectivity()
    }

    suspend fun connect() {
        log(TAG, Severity.Info, "connect() called")
        isManualDisconnect = false
        connectInProgress = true
        reconnectCount = 0
        transition(ConnectionState.BindingNetwork)
        val networkResult = binder.bindCameraNetwork()
        if (networkResult.isFailure) {
            connectInProgress = false
            val reason = networkResult.exceptionOrNull()?.message ?: "unknown reason"
            return setError("Camera network unavailable: $reason")
        }

        val network = networkResult.getOrThrow()
        cameraNetwork = network
        httpClient = OkHttpClient.Builder()
            .socketFactory(network.socketFactory)
            .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .build()
        transition(ConnectionState.ConnectingTcp)
        val socketResult = socketClient.connect(network)
        if (socketResult.isFailure) {
            connectInProgress = false
            binder.unbind()
            val reason = socketResult.exceptionOrNull()?.message ?: "unknown reason"
            return setError("TCP connect failed: $reason")
        }
        log(TAG, Severity.Info, "TCP connected")
        delay(100)

        transition(ConnectionState.SessionStarting)
        val tokenResult = sessionManager.startSession()
        if (tokenResult.isFailure) {
            connectInProgress = false
            binder.unbind()
            val reason = tokenResult.exceptionOrNull()?.message ?: "unknown reason"
            return setError("Start session failed: $reason")
        }

        _uiState.update {
            it.copy(
                token = tokenResult.getOrNull(),
                model = sessionManager.model.value ?: "unknown",
                sessionFlags = sessionManager.flags.value,
                connectionState = ConnectionState.SessionActive,
                error = null
            )
        }

        transition(ConnectionState.Initializing)
        val initSettings = sessionManager.initializeCamera()
        val initParam = initSettings?.param
        if (initParam != null) {
            parseAllSettings(initParam)
            rebuildUiSettings()
        }
        fetchOptionsForKnownSettings()
        refreshFiles(0, 60)
        connectInProgress = false
        reconnectCount = 0
        transition(ConnectionState.CameraReady)
        log(TAG, Severity.Info, "connect() complete, autoStartLive=$autoStartLiveAfterReconnect")
        if (autoStartLiveAfterReconnect) {
            onLiveStateChanged(LiveState.Buffering, "auto-reconnect")
        }
    }

    fun reportConnectionError(error: Throwable) {
        connectInProgress = false
        val message = error.message?.takeIf { it.isNotBlank() }
            ?: error::class.simpleName
            ?: "Unknown connection error"
        setError("Connect failed: $message")
    }

    suspend fun disconnect() {
        log(TAG, Severity.Info, "disconnect() called, isManual=true")
        isManualDisconnect = true
        connectInProgress = false
        stopRecordPolling()
        autoStartLiveAfterReconnect = false
        onLiveStateChanged(LiveState.Stopped, "manual disconnect")
        sessionManager.stopSession()
        socketClient.close()
        binder.unbind()
        settingsCache.clear()
        optionsCache.clear()
        albumMode = false
        _uiState.update { CameraUiState() }
        log(TAG, Severity.Info, "Disconnected")
    }

    suspend fun reconnect(reason: String) {
        if (reconnectInProgress || connectInProgress) return
        reconnectCount++
        if (reconnectCount > maxReconnectAttempts) {
            log(TAG, Severity.Error, "Reconnect limit ($maxReconnectAttempts) reached, giving up")
            setError("Reconnect limit reached")
            return
        }
        reconnectInProgress = true
        transition(ConnectionState.Reconnecting)
        log(TAG, Severity.Warn, "Reconnect requested: $reason (attempt $reconnectCount/$maxReconnectAttempts)")
        autoStartLiveAfterReconnect = uiState.value.liveState != LiveState.Stopped
        try {
            runCatching { disconnect() }
            isManualDisconnect = false
            delay(2000)
            runCatching { connect() }
        } finally {
            reconnectInProgress = false
        }
    }

    suspend fun changeSetting(key: String, value: String) {
        log(TAG, Severity.Debug, "changeSetting key=$key value=$value")
        val token = uiState.value.token ?: return

        // Stop VF before changing setting to avoid AMBA_DEV_BUSY (-21)
        // Camera acks STOP_VF fast but actually stops VF ~500ms later (async notification)
        val signal = CompletableDeferred<Unit>()
        vfStopSignal = signal
        sendAwaitSingleFlight(CameraMessage(msgId = 260, token = token))
        // Wait for actual vf_stop notification from camera, timeout 2s
        withTimeoutOrNull(2000) { signal.await() }
        vfStopSignal = null

        val request = CameraMessage(
            msgId = 2,
            token = token,
            type = key,
            param = JsonPrimitive(value)
        )
        val response = sendAwaitSingleFlight(request)
        val rval = response?.rval ?: 0

        // Restart VF regardless of result
        sendAwaitSingleFlight(CameraMessage(
            msgId = 259, token = token,
            param = JsonPrimitive("none_force")
        ))

        if (!handleRval(rval, "set $key")) return
        settingsCache[key] = value
        rebuildUiSettings()
    }

    suspend fun startRecord() {
        log(TAG, Severity.Debug, "startRecord()")
        if (uiState.value.isRecording) {
            log(TAG, Severity.Warn, "Start record skipped: already recording")
            return
        }
        sendSimple(513)
    }

    suspend fun stopRecord() {
        log(TAG, Severity.Debug, "stopRecord()")
        if (!uiState.value.isRecording) {
            log(TAG, Severity.Warn, "Stop record skipped: not recording")
            return
        }
        sendSimple(514)
        stopRecordPolling()
        refreshFiles(uiState.value.fileWindowFrom, uiState.value.fileWindowTo)
    }

    suspend fun takePhoto() {
        log(TAG, Severity.Debug, "takePhoto()")
        sendSimple(769)
        delay(500)
        refreshFiles(uiState.value.fileWindowFrom, uiState.value.fileWindowTo)
    }

    suspend fun pollRecordStatus() {
        if (!uiState.value.isRecording) return
        sendSimple(515)
    }

    suspend fun refreshSettings() {
        fetchAllSettings()
    }

    suspend fun refreshFiles(from: Int, to: Int) {
        val token = uiState.value.token ?: return
        _uiState.update { it.copy(fileWindowFrom = from, fileWindowTo = to) }
        val basePath = "/tmp/fuse_d/DCIM"
        val dirs = listDirectory(token, basePath, 0, 100)
        val allFiles = mutableListOf<CameraFile>()
        for (dir in dirs) {
            if (!dir.endsWith("/")) continue
            val dirPath = "$basePath/$dir".trimEnd('/')
            val entries = listDirectory(token, dirPath, from, to)
            for (entry in entries) {
                if (entry.endsWith("/")) continue
                allFiles += CameraFile(
                    path = "$dirPath/$entry",
                    name = entry,
                    size = 0L,
                    type = inferType(entry)
                )
            }
        }
        allFiles.sortByDescending { it.name }
        _uiState.update { it.copy(files = allFiles) }
        log(TAG, Severity.Debug, "refreshFiles: found ${allFiles.size} files in ${dirs.size} dirs")
    }

    private suspend fun listDirectory(token: Int, path: String, from: Int, to: Int): List<String> {
        val request = CameraMessage(
            msgId = 1282,
            token = token,
            param = JsonPrimitive(path),
            from = from,
            to = to
        )
        val response = sendAwaitSingleFlight(request) ?: return emptyList()
        return parseListing(response.listing)
    }

    suspend fun download(file: CameraFile) {
        val target = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), file.name)
        if (!hasEnoughSpace(file.size, target.parentFile ?: context.filesDir)) {
            setError("No free space for ${file.name}")
            return
        }

        val token = uiState.value.token ?: return
        val httpPath = file.path.removePrefix("/tmp/fuse_d")
        val url = "http://192.168.42.1$httpPath"
        log(TAG, Severity.Debug, "download url=$url network=${cameraNetwork != null}")

        // Stop VF so camera HTTP server can serve files
        val signal = CompletableDeferred<Unit>()
        vfStopSignal = signal
        sendAwaitSingleFlight(CameraMessage(msgId = 260, token = token))
        withTimeoutOrNull(2000) { signal.await() }
        vfStopSignal = null

        val request = Request.Builder().url(url).build()
        _uiState.update { it.copy(downloading = it.downloading + (file.name to 0)) }

        val downloadResult = runCatching {
            kotlinx.coroutines.withContext(Dispatchers.IO) {
                httpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        val errBody = response.body?.string()?.take(200) ?: ""
                        error("HTTP ${response.code} ${response.message} body=$errBody")
                    }
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
            }
        }

        // Restart VF regardless of download result
        sendAwaitSingleFlight(CameraMessage(
            msgId = 259, token = token,
            param = JsonPrimitive("none_force")
        ))

        downloadResult.onFailure { e ->
            _uiState.update { state -> state.copy(downloading = state.downloading - file.name) }
            log(TAG, Severity.Error, "Download failed: ${e::class.simpleName}: ${e.message}")
            android.util.Log.e(TAG, "Download stacktrace", e)
            _uiState.update { it.copy(error = "Download failed: ${e::class.simpleName}: ${e.message}") }
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
        log(TAG, Severity.Info, "Downloaded ${file.name} -> ${target.absolutePath}")
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

    // --- Private: send helpers ---

    private suspend fun sendSimple(msgId: Int) {
        log(TAG, Severity.Debug, "sendSimple msgId=$msgId")
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

    // --- Private: settings ---

    private suspend fun fetchAllSettings() {
        val token = uiState.value.token ?: return
        val response = sendAwaitSingleFlight(CameraMessage(msgId = 3, token = token))
        val param = response?.param ?: return
        parseAllSettings(param)
        rebuildUiSettings()
    }

    private suspend fun fetchOptionsForKnownSettings() {
        val token = uiState.value.token ?: return
        for (settingName in OPTIONS_TO_QUERY) {
            val response = sendAwaitSingleFlight(CameraMessage(
                msgId = 9,
                token = token,
                param = JsonPrimitive(settingName)
            )) ?: continue
            parseOptionResponse(settingName, response)
        }
        rebuildUiSettings()
    }

    private fun parseAllSettings(param: JsonElement) {
        val arr = param as? JsonArray ?: return
        for (item in arr) {
            val obj = item as? JsonObject ?: continue
            for ((key, value) in obj) {
                val strValue = (value as? JsonPrimitive)?.contentOrNull ?: continue
                if (!albumMode) {
                    settingsCache[key] = strValue
                }
            }
        }
    }

    private fun parseOptionResponse(settingName: String, message: CameraMessage) {
        val options = (message.options as? JsonArray)
            ?.mapNotNull { (it as? JsonPrimitive)?.contentOrNull }
        if (!options.isNullOrEmpty()) {
            optionsCache[settingName] = options
        }
    }

    private fun rebuildUiSettings() {
        val result = DISPLAYED_SETTINGS.mapNotNull { key ->
            val value = settingsCache[key] ?: return@mapNotNull null
            CameraSetting(
                key = key,
                value = value,
                options = optionsCache[key].orEmpty()
            )
        }
        _uiState.update { it.copy(settings = result) }
    }

    // --- Private: notifications (msg_id=7) ---

    private fun handleNotification(message: CameraMessage) {
        val notifType = message.type ?: return
        val paramStr = (message.param as? JsonPrimitive)?.contentOrNull
        val valueStr = (message.value as? JsonPrimitive)?.contentOrNull

        log(TAG, Severity.Info, "notify type=$notifType param=$paramStr value=$valueStr")

        when (notifType) {
            "start_video_record" -> {
                _uiState.update { it.copy(isRecording = true) }
                startRecordPollingIfNeeded()
            }
            "video_record_complete" -> {
                _uiState.update { it.copy(isRecording = false) }
                stopRecordPolling()
            }
            "start_photo_capture" -> {
                _uiState.update { it.copy(appStatus = "capture") }
            }
            "photo_taken", "burst_complete", "precise_cont_complete" -> {
                _uiState.update { it.copy(appStatus = "idle") }
            }
            "sd_card_status" -> {
                _uiState.update { it.copy(sdCardStatus = paramStr) }
            }
            "CARD_NOT_EXIST", "CARD_REMOVED" -> {
                _uiState.update { it.copy(sdCardStatus = "remove") }
            }
            "STORAGE_RUNOUT" -> {
                setError("SD card storage full")
            }
            "battery" -> {
                val percent = paramStr?.toIntOrNull()
                if (percent != null) {
                    _uiState.update { it.copy(batteryPercent = percent) }
                }
            }
            "adapter_status" -> {
                _uiState.update { it.copy(adapterConnected = paramStr == "1") }
            }
            "switch_to_cap_mode" -> {
                if (!albumMode) settingsCache["system_mode"] = "capture"
                rebuildUiSettings()
            }
            "switch_to_rec_mode" -> {
                if (!albumMode) settingsCache["system_mode"] = "record"
                rebuildUiSettings()
            }
            "setting_changed" -> {
                val settingKey = paramStr ?: return
                val newValue = valueStr ?: return
                if (!albumMode) {
                    settingsCache[settingKey] = newValue
                }
                applyRelatedSettings(message.related)
                rebuildUiSettings()
            }
            "enter_album" -> {
                albumMode = true
            }
            "exit_album" -> {
                albumMode = false
            }
            "start_usb_storage", "enter_usb_storage" -> {
                scope.launch { reconnect("USB storage mode") }
            }
            "start_fwupdate", "enter_fwupdate" -> {
                scope.launch { reconnect("FW update mode") }
            }
            "wifi_will_shutdown" -> {
                scope.launch { reconnect("WiFi shutdown") }
            }
            "vf_start" -> log(TAG, Severity.Debug, "Viewfinder started")
            "vf_stop" -> {
                log(TAG, Severity.Debug, "Viewfinder stopped")
                vfStopSignal?.complete(Unit)
            }
            "rec_time" -> {
                // record time update from camera (seconds)
            }
        }
    }

    private fun applyRelatedSettings(related: JsonElement?) {
        val arr = related as? JsonArray ?: return
        for (item in arr) {
            val obj = item as? JsonObject ?: continue
            for ((key, value) in obj) {
                val strValue = (value as? JsonPrimitive)?.contentOrNull ?: continue
                if (!albumMode) {
                    settingsCache[key] = strValue
                }
            }
        }
    }

    // --- Private: incoming message observer ---

    private fun observeIncoming() {
        scope.launch {
            socketClient.incoming.collect { msg ->
                when (msg.msgId) {
                    7 -> handleNotification(msg)
                    1793 -> {
                        sessionManager.handleIncomingHeartbeat(msg)
                    }
                    1282 -> {
                        // file listing handled in refreshFiles directly
                    }
                    13 -> {
                        val battery = (msg.param as? JsonPrimitive)?.contentOrNull?.toIntOrNull()
                        if (battery != null) {
                            _uiState.update { it.copy(batteryPercent = battery) }
                        }
                    }
                }
                // canSendNext on any response (except heartbeat, handled in SessionManager)
                if (msg.msgId != 1793 && msg.msgId != 7) {
                    socketClient.notifyCanSendNext()
                }
                // rval error handling (skip heartbeat — camera may not support 16777244)
                val rval = msg.rval
                if (rval != null && rval != 0 && msg.msgId != 16777244) {
                    log(TAG, Severity.Warn, "rval=$rval for msg_id=${msg.msgId}")
                    handleRval(rval, "incoming msg_id=${msg.msgId}")
                }
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
                        log(TAG, Severity.Debug, "socket connected=true")
                    } else {
                        val hasSession = uiState.value.token != null
                        log(TAG, Severity.Warn, "socket connected=false, hasSession=$hasSession, isManual=$isManualDisconnect")
                        if (!isManualDisconnect && hasSession) {
                            reconnect("tcp disconnected")
                        }
                    }
                }
        }
    }

    // --- Private: file parsing ---

    private fun parseListing(listing: JsonElement?): List<String> {
        val arr = listing as? JsonArray ?: return emptyList()
        return arr.flatMap { item ->
            val obj = item as? JsonObject ?: return@flatMap emptyList()
            obj.keys.toList()
        }
    }

    private fun inferType(name: String): String = when {
        name.endsWith(".mp4", true) -> "video"
        name.endsWith(".jpg", true) || name.endsWith(".jpeg", true) -> "photo"
        else -> "unknown"
    }

    // --- Private: record polling ---

    private fun startRecordPollingIfNeeded() {
        if (recordPollJob?.isActive == true) return
        recordPollJob = scope.launch {
            while (uiState.value.isRecording) {
                sendSimple(515)
                delay(RECORD_POLL_INTERVAL_MS)
            }
        }
    }

    private fun stopRecordPolling() {
        recordPollJob?.cancel()
        recordPollJob = null
    }

    // --- Private: rval / error handling ---

    private fun validateSessionFlags(flags: SessionFlags) {
        if (flags.usbstorage) {
            scope.launch { reconnect("USB storage active") }
        }
        if (flags.fwupdate) {
            scope.launch { reconnect("FW update active") }
        }
    }

    private suspend fun handleRval(rval: Int, context: String): Boolean {
        return when (rval) {
            0 -> true
            -21 -> {
                log(TAG, Severity.Warn, "$context busy (-21)")
                false
            }
            -4 -> {
                log(TAG, Severity.Warn, "$context session lost (-4), reconnect")
                reconnect("session lost")
                false
            }
            -3 -> {
                log(TAG, Severity.Error, "$context AMBA_UNAVAILABLE (-3), closing session")
                setError("$context: AMBA_UNAVAILABLE")
                scope.launch { disconnect() }
                false
            }
            else -> {
                setError("$context failed: rval=$rval")
                false
            }
        }
    }

    // --- Private: utility ---

    private fun hasEnoughSpace(requiredBytes: Long, atDir: File): Boolean {
        val stat = StatFs(atDir.absolutePath)
        val freeBytes = stat.availableBytes
        return freeBytes > requiredBytes + 1_000_000
    }

    private fun transition(next: ConnectionState) {
        _uiState.update { it.copy(connectionState = next, error = null) }
        log(TAG, Severity.Info, "state->$next")
    }

    private fun setError(message: String) {
        val shouldMarkLiveError = when (uiState.value.liveState) {
            LiveState.Buffering, LiveState.Playing -> true
            LiveState.Stopped, LiveState.Error -> false
        }
        _uiState.update { it.copy(connectionState = ConnectionState.Error, error = message) }
        if (shouldMarkLiveError) {
            onLiveStateChanged(LiveState.Error, message)
        }
        log(TAG, Severity.Error, message)
    }

    private fun log(tag: String, severity: Severity, message: String) {
        when (severity) {
            Severity.Debug -> android.util.Log.d(tag, message)
            Severity.Info -> android.util.Log.i(tag, message)
            Severity.Warn -> android.util.Log.w(tag, message)
            Severity.Error -> android.util.Log.e(tag, message)
        }
        _uiState.update {
            it.copy(logs = (it.logs + DebugLogEntry(System.currentTimeMillis(), tag, severity, message)).takeLast(400))
        }
    }
}

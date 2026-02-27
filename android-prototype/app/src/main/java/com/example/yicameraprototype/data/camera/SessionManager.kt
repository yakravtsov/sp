package com.example.yicameraprototype.data.camera

import com.example.yicameraprototype.domain.CameraMessage
import com.example.yicameraprototype.domain.SessionFlags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.intOrNull

class SessionManager(private val socketClient: CameraSocketClient) {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _token = MutableStateFlow<Int?>(null)
    private val _flags = MutableStateFlow(SessionFlags())
    private var heartbeatJob: Job? = null
    private var heartbeatWatchdogJob: Job? = null
    private val inFlightMutex = Mutex()
    private var lastIncomingHeartbeatTs = 0L
    private var lastOutgoingCommandTs = 0L

    val token: StateFlow<Int?> = _token.asStateFlow()
    val flags: StateFlow<SessionFlags> = _flags.asStateFlow()

    suspend fun startSession(): Result<Int> {
        val request = CameraMessage(msgId = 257, token = 0)
        return sendAndAwait(request).map { message ->
            val value = (message.param as? JsonPrimitive)?.intOrNull ?: 0
            _token.value = value
            parseFlags(message)
            startHeartbeat()
            startHeartbeatWatchdog()
            value
        }
    }

    suspend fun stopSession() {
        socketClient.send(CameraMessage(msgId = 258, token = _token.value ?: 0))
        _token.value = null
        _flags.value = SessionFlags()
        heartbeatJob?.cancel()
        heartbeatWatchdogJob?.cancel()
    }

    suspend fun initializeCameraForJ22() {
        val currentToken = _token.value ?: return
        sendAndAwait(CameraMessage(msgId = 3, token = currentToken))
        sendAndAwait(CameraMessage(msgId = 2, token = currentToken, param = JsonObject(mapOf("camera_clock" to JsonPrimitive(System.currentTimeMillis() / 1000)))))
        sendAndAwait(CameraMessage(msgId = 259, token = currentToken, param = JsonPrimitive("none_force")))
        sendAndAwait(CameraMessage(msgId = 9, token = currentToken))
    }

    suspend fun sendAndTrack(message: CameraMessage) {
        socketClient.send(message)
        touchCommandTime()
    }

    suspend fun sendAndAwait(message: CameraMessage, timeoutMs: Long = 2500): Result<CameraMessage> {
        return inFlightMutex.withLock {
            socketClient.send(message)
            touchCommandTime()
            waitForResponse(message, timeoutMs)
        }
    }

    suspend fun handleIncomingHeartbeat(message: CameraMessage) {
        if (message.msgId != 1793) return
        lastIncomingHeartbeatTs = System.currentTimeMillis()
        socketClient.send(CameraMessage(msgId = 1793, token = _token.value ?: 0))
    }

    private fun startHeartbeat() {
        heartbeatJob?.cancel()
        heartbeatJob = scope.launch {
            while (true) {
                delay(1000)
                val tokenValue = _token.value ?: continue
                val sinceLastCommand = System.currentTimeMillis() - lastOutgoingCommandTs
                if (sinceLastCommand >= 5000) {
                    socketClient.send(CameraMessage(msgId = 16777244, token = tokenValue))
                    touchCommandTime()
                }
            }
        }
    }

    private fun startHeartbeatWatchdog() {
        heartbeatWatchdogJob?.cancel()
        heartbeatWatchdogJob = scope.launch {
            while (true) {
                delay(2000)
                if (_token.value != null && lastIncomingHeartbeatTs > 0) {
                    val delta = System.currentTimeMillis() - lastIncomingHeartbeatTs
                    if (delta > 12000) {
                        _token.value = null
                        break
                    }
                }
            }
        }
    }

    private fun parseFlags(message: CameraMessage) {
        val p = message.param as? JsonObject ?: return
        _flags.value = SessionFlags(
            album = (p["album"] as? JsonPrimitive)?.booleanOrNull ?: false,
            fwupdate = (p["fwupdate"] as? JsonPrimitive)?.booleanOrNull ?: false,
            mvrecover = (p["mvrecover"] as? JsonPrimitive)?.booleanOrNull ?: false,
            sdformat = (p["sdformat"] as? JsonPrimitive)?.booleanOrNull ?: false,
            sdoptimize = (p["sdoptimize"] as? JsonPrimitive)?.booleanOrNull ?: false,
            usbstorage = (p["usbstorage"] as? JsonPrimitive)?.booleanOrNull ?: false,
            voiceControl = (p["voice_control"] as? JsonPrimitive)?.booleanOrNull ?: false,
            live = (p["live"] as? JsonPrimitive)?.booleanOrNull ?: false
        )
    }

    private suspend fun waitForResponse(request: CameraMessage, timeoutMs: Long): Result<CameraMessage> =
        withTimeoutOrNull(timeoutMs) {
            runCatching {
                socketClient.incoming
                    .filter { incoming ->
                        val sameSeq = request.seq != null && incoming.seq != null && request.seq == incoming.seq
                        sameSeq || (incoming.msgId == request.msgId && incoming.token == request.token)
                    }
                    .first()
            }
        } ?: Result.failure(IllegalStateException("timeout waiting response msg_id=${request.msgId}"))

    private fun touchCommandTime() {
        lastOutgoingCommandTs = System.currentTimeMillis()
    }
}

package com.example.yicameraprototype.data.camera

import android.util.Log
import com.example.yicameraprototype.domain.CameraMessage
import com.example.yicameraprototype.domain.SessionFlags
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.intOrNull

class SessionManager(private val socketClient: CameraSocketClient) {
    companion object {
        private const val TAG = "SessionManager"
        private const val MAX_START_SESSION_RETRIES = 3
    }

    private val _token = MutableStateFlow<Int?>(null)
    private val _model = MutableStateFlow<String?>(null)
    private val _flags = MutableStateFlow(SessionFlags())
    private val inFlightMutex = Mutex()

    val token: StateFlow<Int?> = _token.asStateFlow()
    val model: StateFlow<String?> = _model.asStateFlow()
    val flags: StateFlow<SessionFlags> = _flags.asStateFlow()

    suspend fun startSession(): Result<Int> {
        var lastError: Throwable? = null
        repeat(MAX_START_SESSION_RETRIES) { attempt ->
            Log.d(TAG, "startSession: attempt ${attempt + 1}")
            val request = CameraMessage(
                msgId = 257,
                token = 0,
                param = JsonPrimitive(0),
                heartbeat = 1
            )
            val result = sendAndAwait(request)
            val message = result.getOrElse {
                lastError = it
                Log.w(TAG, "startSession: attempt ${attempt + 1} failed: ${it.message}")
                return@repeat
            }
            val rval = message.rval ?: 0
            if (rval == -3) {
                Log.w(TAG, "startSession: AMBA_UNAVAILABLE (-3), retry after delay")
                delay(2000L * (attempt + 1))
                lastError = IllegalStateException("AMBA_UNAVAILABLE")
                return@repeat
            }
            val sessionToken = (message.param as? JsonPrimitive)?.intOrNull
            if (rval != 0 || sessionToken == null || sessionToken < 0) {
                lastError = IllegalStateException("Start session rejected: rval=$rval, param=${message.param}")
                Log.w(TAG, "startSession: ${lastError!!.message}")
                return@repeat
            }
            _token.value = sessionToken
            _model.value = message.model
            parseFlags(message)
            Log.d(TAG, "startSession: token=$sessionToken, model=${message.model}")
            return Result.success(sessionToken)
        }
        return Result.failure(lastError ?: IllegalStateException("Start session failed after retries"))
    }

    suspend fun stopSession() {
        val tok = _token.value ?: return
        socketClient.drainQueue()
        socketClient.sendPriority(CameraMessage(msgId = 258, token = tok))
        delay(500)
        _token.value = null
        _model.value = null
        _flags.value = SessionFlags()
    }

    suspend fun initializeCamera(): CameraMessage? {
        val tok = _token.value ?: return null
        val settingsResponse = sendAndAwait(CameraMessage(msgId = 3, token = tok)).getOrNull()
        val clockStr = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.US)
            .format(java.util.Date())
        sendAndAwait(CameraMessage(
            msgId = 2, token = tok,
            type = "camera_clock", param = JsonPrimitive(clockStr)
        ))
        sendAndAwait(CameraMessage(
            msgId = 259, token = tok,
            param = JsonPrimitive("none_force")
        ))
        return settingsResponse
    }

    suspend fun sendAndAwait(message: CameraMessage, timeoutMs: Long = 3000): Result<CameraMessage> {
        return inFlightMutex.withLock {
            socketClient.enqueue(message)
            waitForResponse(message.msgId, timeoutMs)
        }
    }

    fun handleIncomingHeartbeat(message: CameraMessage) {
        if (message.msgId != 1793) return
        val tok = _token.value ?: return
        socketClient.sendPriority(CameraMessage(msgId = 1793, token = tok, rval = 0))
        socketClient.notifyCanSendNext()
        Log.d(TAG, "handleIncomingHeartbeat: acked with rval=0, canSendNext=true")
    }

    private fun parseFlags(message: CameraMessage) {
        _flags.value = SessionFlags(
            album = message.album == 1,
            fwupdate = message.fwupdate == 1,
            mvrecover = message.mvrecover == 1,
            sdformat = message.sdformat == 1,
            sdoptimize = message.sdoptimize == 1,
            usbstorage = message.usbstorage == 1,
            voiceControl = message.voiceControl == 1,
            live = message.live == 1
        )
    }

    private suspend fun waitForResponse(msgId: Int, timeoutMs: Long): Result<CameraMessage> =
        withTimeoutOrNull(timeoutMs) {
            runCatching {
                socketClient.incoming
                    .filter { it.msgId == msgId }
                    .first()
            }
        } ?: Result.failure(IllegalStateException("timeout waiting response msg_id=$msgId"))
}

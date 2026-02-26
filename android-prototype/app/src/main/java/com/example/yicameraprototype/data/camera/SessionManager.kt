package com.example.yicameraprototype.data.camera

import com.example.yicameraprototype.domain.CameraMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.intOrNull

class SessionManager(
    private val socketClient: CameraSocketClient
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private var heartbeatJob: Job? = null

    private val _token = MutableStateFlow<Int?>(null)
    val token: StateFlow<Int?> = _token.asStateFlow()

    suspend fun startSession(): Result<Int> {
        socketClient.send(CameraMessage(msgId = 257, token = 0))
        return waitFor(257).map { message ->
            val value = (message.param as? JsonPrimitive)?.intOrNull ?: 0
            _token.value = value
            startHeartbeat()
            value
        }
    }

    suspend fun stopSession() {
        socketClient.send(CameraMessage(msgId = 258, token = _token.value ?: 0))
        _token.value = null
        heartbeatJob?.cancel()
    }

    suspend fun initializeCameraForJ22() {
        val token = _token.value ?: return
        socketClient.send(CameraMessage(msgId = 3, token = token))
        socketClient.send(CameraMessage(msgId = 2, token = token, param = JsonPrimitive("camera_clock")))
        socketClient.send(CameraMessage(msgId = 259, token = token, param = JsonPrimitive("none_force")))
        socketClient.send(CameraMessage(msgId = 9, token = token))
    }

    private fun startHeartbeat() {
        heartbeatJob?.cancel()
        heartbeatJob = scope.launch {
            while (true) {
                delay(5000)
                _token.value?.let { socketClient.send(CameraMessage(msgId = 16777244, token = it)) }
            }
        }
    }

    private suspend fun waitFor(msgId: Int): Result<CameraMessage> = runCatching {
        socketClient.incoming.first { message ->
            if (message.msgId == 1793) {
                socketClient.send(CameraMessage(msgId = 1793, token = _token.value ?: 0))
            }
            message.msgId == msgId
        }
    }
}

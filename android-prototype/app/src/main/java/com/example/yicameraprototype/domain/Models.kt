package com.example.yicameraprototype.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class ConnectionState {
    Disconnected,
    Connecting,
    Connected,
    SessionActive,
    CameraReady,
    Error
}

enum class LiveState {
    Stopped,
    Buffering,
    Playing,
    Error
}

@Serializable
data class CameraMessage(
    @SerialName("msg_id") val msgId: Int,
    val token: Int? = null,
    val param: kotlinx.serialization.json.JsonElement? = null,
    val rval: Int? = null
)

data class CameraSetting(
    val key: String,
    val value: String,
    val options: List<String> = emptyList()
)

data class CameraFile(
    val path: String,
    val name: String,
    val size: Long,
    val type: String,
    val date: String? = null
)

data class CameraUiState(
    val connectionState: ConnectionState = ConnectionState.Disconnected,
    val liveState: LiveState = LiveState.Stopped,
    val logs: List<String> = emptyList(),
    val token: Int? = null,
    val model: String = "YDXJ_v22",
    val batteryPercent: Int? = null,
    val isRecording: Boolean = false,
    val settings: List<CameraSetting> = emptyList(),
    val files: List<CameraFile> = emptyList(),
    val downloading: Map<String, Int> = emptyMap(),
    val error: String? = null
)

package com.example.yicameraprototype.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

enum class ConnectionState {
    Disconnected,
    BindingNetwork,
    ConnectingTcp,
    Connected,
    SessionStarting,
    SessionActive,
    Initializing,
    CameraReady,
    Reconnecting,
    Error
}

enum class LiveState {
    Stopped,
    Buffering,
    Playing,
    Error
}

enum class Severity {
    Debug,
    Info,
    Warn,
    Error
}

@Serializable
data class CameraMessage(
    @SerialName("msg_id") val msgId: Int,
    val token: Int? = null,
    val rval: Int? = null,
    val type: String? = null,
    val param: JsonElement? = null,
    val seq: Int? = null
)

data class CameraSetting(
    val key: String,
    val value: String,
    val options: List<String> = emptyList(),
    val related: Int = 0
)

data class CameraFile(
    val path: String,
    val name: String,
    val size: Long,
    val type: String,
    val date: String? = null,
    val downloaded: Boolean = false
)

data class SessionFlags(
    val album: Boolean = false,
    val fwupdate: Boolean = false,
    val mvrecover: Boolean = false,
    val sdformat: Boolean = false,
    val sdoptimize: Boolean = false,
    val usbstorage: Boolean = false,
    val voiceControl: Boolean = false,
    val live: Boolean = false
)

data class DebugLogEntry(
    val ts: Long,
    val tag: String,
    val severity: Severity,
    val message: String
)

data class CameraUiState(
    val connectionState: ConnectionState = ConnectionState.Disconnected,
    val liveState: LiveState = LiveState.Stopped,
    val token: Int? = null,
    val model: String = "YDXJ_v22",
    val batteryPercent: Int? = null,
    val adapterConnected: Boolean? = null,
    val sdCardStatus: String? = null,
    val appStatus: String? = null,
    val isRecording: Boolean = false,
    val settings: List<CameraSetting> = emptyList(),
    val files: List<CameraFile> = emptyList(),
    val fileWindowFrom: Int = 0,
    val fileWindowTo: Int = 60,
    val downloading: Map<String, Int> = emptyMap(),
    val sessionFlags: SessionFlags = SessionFlags(),
    val logs: List<DebugLogEntry> = emptyList(),
    val error: String? = null
)

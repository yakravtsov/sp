package com.example.yicameraprototype.uilab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.yicameraprototype.R

enum class CameraMode {
    VIDEO, PHOTO
}

enum class RecordingState {
    IDLE, RECORDING
}

enum class AspectRatioMode {
    RATIO_4_3, RATIO_16_9
}

enum class CameraSwitch {
    FRONT, BACK
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var cameraMode by remember { mutableStateOf(CameraMode.VIDEO) }
    var recordingState by remember { mutableStateOf(RecordingState.IDLE) }
    var aspectRatio by remember { mutableStateOf(AspectRatioMode.RATIO_4_3) }
    var cameraSwitch by remember { mutableStateOf(CameraSwitch.BACK) }
    var wifiConnected by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(
                    when (aspectRatio) {
                        AspectRatioMode.RATIO_4_3 -> 4f / 3f
                        AspectRatioMode.RATIO_16_9 -> 16f / 9f
                    }
                )
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    when {
                        cameraMode == CameraMode.VIDEO && recordingState == RecordingState.IDLE -> {
                            recordingState = RecordingState.RECORDING
                        }
                        cameraMode == CameraMode.VIDEO && recordingState == RecordingState.RECORDING -> {
                            recordingState = RecordingState.IDLE
                        }
                        cameraMode == CameraMode.PHOTO -> {
                        }
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = when {
                            cameraMode == CameraMode.VIDEO && recordingState == RecordingState.IDLE -> R.drawable.screen_record_24px
                            cameraMode == CameraMode.VIDEO && recordingState == RecordingState.RECORDING -> R.drawable.stop_circle_24px
                            else -> R.drawable.photo_camera_24px
                        }
                    ),
                    contentDescription = "Record/Photo"
                )
            }

            IconButton(
                onClick = {
                    aspectRatio = when (aspectRatio) {
                        AspectRatioMode.RATIO_4_3 -> AspectRatioMode.RATIO_16_9
                        AspectRatioMode.RATIO_16_9 -> AspectRatioMode.RATIO_4_3
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = when (aspectRatio) {
                            AspectRatioMode.RATIO_4_3 -> R.drawable.panorama_horizontal_24px
                            AspectRatioMode.RATIO_16_9 -> R.drawable.panorama_wide_angle_24px
                        }
                    ),
                    contentDescription = "Aspect Ratio"
                )
            }

            IconButton(
                onClick = {
                    cameraSwitch = when (cameraSwitch) {
                        CameraSwitch.FRONT -> CameraSwitch.BACK
                        CameraSwitch.BACK -> CameraSwitch.FRONT
                    }
                    cameraMode = when (cameraMode) {
                        CameraMode.PHOTO -> CameraMode.VIDEO
                        CameraMode.VIDEO -> CameraMode.PHOTO
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = when (cameraMode) {
                            CameraMode.PHOTO -> R.drawable.switch_camera_24px
                            CameraMode.VIDEO -> R.drawable.switch_video_24px
                        }
                    ),
                    contentDescription = "Switch Camera/Mode"
                )
            }

            IconButton(
                onClick = {
                    wifiConnected = !wifiConnected
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (wifiConnected) R.drawable.android_wifi_4_bar_off_24px else R.drawable.android_wifi_4_bar_plus_24px
                    ),
                    contentDescription = "WiFi"
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.zoom_out_map_24px),
                    contentDescription = "Zoom"
                )
            }
        }
    }
}

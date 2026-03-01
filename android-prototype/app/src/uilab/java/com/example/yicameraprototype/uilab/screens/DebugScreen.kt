package com.example.yicameraprototype.uilab.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource
import com.example.yicameraprototype.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun DebugScreen(
    modifier: Modifier = Modifier
) {
    var logText by remember {
        mutableStateOf(
            """
            [2026-02-28 21:27:15] INFO: Camera connection established
            [2026-02-28 21:27:16] DEBUG: Session token: abc123xyz
            [2026-02-28 21:27:17] INFO: Live stream started
            [2026-02-28 21:27:18] DEBUG: Video resolution: 1920x1080
            [2026-02-28 21:27:19] DEBUG: Frame rate: 30 fps
            [2026-02-28 21:27:20] INFO: Battery level: 85%
            [2026-02-28 21:27:21] DEBUG: WiFi signal strength: -45 dBm
            [2026-02-28 21:27:22] INFO: SD card available: 24.5 GB
            [2026-02-28 21:27:23] DEBUG: Recording state: idle
            [2026-02-28 21:27:24] INFO: Camera ready
            [2026-02-28 21:27:25] DEBUG: Temperature: 42°C
            [2026-02-28 21:27:26] INFO: Firmware version: 1.2.3
            [2026-02-28 21:27:27] DEBUG: Network latency: 12ms
            [2026-02-28 21:27:28] INFO: All systems operational
            """.trimIndent()
        )
    }

    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    clipboardManager.setText(AnnotatedString(logText))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.copy_all_24px),
                    contentDescription = "Copy"
                )
            }

            IconButton(
                onClick = {
                    logText = ""
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete_24px),
                    contentDescription = "Clear"
                )
            }
        }

        OutlinedTextField(
            value = logText,
            onValueChange = { logText = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            textStyle = MaterialTheme.typography.bodySmall,
            readOnly = false
        )
    }
}

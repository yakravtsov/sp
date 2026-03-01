package com.example.yicameraprototype.uilab.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.yicameraprototype.R

@Composable
fun TopInfoBar(
    wifiConnected: Boolean,
    cameraReady: Boolean,
    batteryLevel: Int,
    modifier: Modifier = Modifier
) {
    val successColor = MaterialTheme.colorScheme.primary
    val errorColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            //.background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = if (wifiConnected) R.drawable.wifi_24px else R.drawable.wifi_off_24px),
                contentDescription = if (wifiConnected) "WiFi Connected" else "WiFi Disconnected",
                modifier = Modifier.size(20.dp),
                tint = if (wifiConnected) successColor else errorColor
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier.size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = if (cameraReady) R.drawable.check_circle_24px else R.drawable.cancel_24px),
                    contentDescription = if (cameraReady) "Camera Ready" else "Camera Error",
                    modifier = Modifier.size(20.dp),
                    tint = if (cameraReady) successColor else errorColor
                )
            }

            Box(
                modifier = Modifier.size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = getBatteryIcon(batteryLevel)),
                    contentDescription = "Battery $batteryLevel%",
                    modifier = Modifier.size(20.dp),
                    tint = getBatteryColor(batteryLevel)
                )
            }
        }
    }
}

private fun getBatteryIcon(level: Int): Int {
    return when {
        level <= 10 -> R.drawable.battery_android_alert_24px
        level in 11..20 -> R.drawable.battery_android_frame_1_24px
        level in 21..35 -> R.drawable.battery_android_frame_2_24px
        level in 36..50 -> R.drawable.battery_android_frame_3_24px
        level in 51..65 -> R.drawable.battery_android_frame_4_24px
        level in 66..80 -> R.drawable.battery_android_frame_5_24px
        level in 81..94 -> R.drawable.battery_android_frame_6_24px
        else -> R.drawable.battery_android_frame_full_24px
    }
}

@Composable
private fun getBatteryColor(level: Int): Color {
    return when {

        level <= 50 -> MaterialTheme.colorScheme.error
        //level <= 65 -> Color(0xFFFFCA28)
        level <= 80 -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.primary
    }
}

@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { onNavigate("home") },
            icon = { Icon(painter = painterResource(R.drawable.home_24px), contentDescription = null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentRoute == "settings",
            onClick = { onNavigate("settings") },
            icon = { Icon(painter = painterResource(R.drawable.settings_24px), contentDescription = null) },
            label = { Text("Settings") }
        )
        NavigationBarItem(
            selected = currentRoute == "gallery",
            onClick = { onNavigate("gallery") },
            icon = { Icon(painter = painterResource(R.drawable.photo_library_24px), contentDescription = null) },
            label = { Text("Gallery") }
        )
        NavigationBarItem(
            selected = currentRoute == "debug",
            onClick = { onNavigate("debug") },
            icon = { Icon(painter = painterResource(R.drawable.frame_bug_24px), contentDescription = null) },
            label = { Text("Debug") }
        )
    }
}

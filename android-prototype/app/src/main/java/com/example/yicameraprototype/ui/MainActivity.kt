package com.example.yicameraprototype.ui

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.yicameraprototype.domain.CameraFile
import com.example.yicameraprototype.domain.CameraSetting
import com.example.yicameraprototype.domain.ConnectionState
import com.example.yicameraprototype.domain.LiveState
import com.example.yicameraprototype.ui.theme.YiCameraPrototypeTheme
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    private val vm by viewModels<MainViewModel>()

    override fun onResume() {
        super.onResume()
        vm.onAppResumed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YiCameraPrototypeTheme {
                val state by vm.uiState.collectAsState()

                LaunchedEffect(Unit) {
                    vm.oneShotIntents.collect { intent ->
                        startActivity(Intent.createChooser(intent, "Open with"))
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        item {
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text("Connection: ${state.connectionState}")
                                Text("Model: ${state.model}, token=${state.token ?: "-"}")
                                Text("Live: ${state.liveState}, recording=${state.isRecording}")
                                Text("Battery: ${state.batteryPercent?.toString() ?: "-"}% , adapter=${state.adapterConnected ?: "-"}")
                                Text("Camera status: sd=${state.sdCardStatus ?: "-"}, app=${state.appStatus ?: "-"}")
                                Text("Flags: live=${state.sessionFlags.live}, sdformat=${state.sessionFlags.sdformat}, album=${state.sessionFlags.album}")
                            }
                        }

                        item {
                            Text("Connection controls", style = MaterialTheme.typography.titleMedium)
                        }

                        item {
                            val ssid by vm.wifiSsid.collectAsState()
                            Text(
                                text = ssid ?: "WiFi не подключен",
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (ssid != null) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.error
                            )
                        }

                        item {
                            val connState = state.connectionState
                            val isConnecting = connState == ConnectionState.BindingNetwork ||
                                connState == ConnectionState.ConnectingTcp ||
                                connState == ConnectionState.SessionStarting ||
                                connState == ConnectionState.Initializing ||
                                connState == ConnectionState.Reconnecting
                            val isConnected = connState == ConnectionState.Connected ||
                                connState == ConnectionState.SessionActive ||
                                connState == ConnectionState.CameraReady

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Button(
                                    onClick = { if (isConnected) vm.disconnect() else vm.connect() },
                                    enabled = !isConnecting,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        when {
                                            isConnecting -> "Connecting…"
                                            isConnected -> "Disconnect"
                                            else -> "Connect"
                                        }
                                    )
                                }
                            }
                        }

                        item { Text("Capture controls", style = MaterialTheme.typography.titleMedium) }
                        item {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Button(
                                    onClick = { if (state.isRecording) vm.stopRecord() else vm.startRecord() },
                                    enabled = state.connectionState == ConnectionState.CameraReady,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(if (state.isRecording) "Stop Rec" else "Start Rec")
                                }
                            }
                        }
                        item {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Button(onClick = { vm.takePhoto() }, modifier = Modifier.weight(1f)) { Text("Photo") }
                                Button(onClick = { vm.pollRecordStatus() }, modifier = Modifier.weight(1f)) { Text("Poll Rec") }
                                /*Button(onClick = vm::exportLogs, modifier = Modifier.weight(1f)) { Text("Export Logs") }*/
                            }
                        }

                        item {
                            LivePlayer(
                                player = vm.player,
                                liveState = state.liveState,
                                onStartLive = vm::startLive,
                                onStopLive = vm::stopLive
                            )
                        }

                        item {
                            Settings(state.settings, vm::setSetting)
                        }

                        item {
                            Files(
                                files = state.files,
                                downloading = state.downloading,
                                refresh = vm::refreshFiles,
                                nextPage = vm::refreshFilesNextPage,
                                download = vm::download,
                                httpClient = vm.cameraHttpClient
                            )
                        }

                        item { Text("Tech log", style = MaterialTheme.typography.titleMedium) }
                        items(state.logs.takeLast(120)) { line ->
                            Text("${line.tag}/${line.severity}: ${line.message}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LivePlayer(
    player: ExoPlayer,
    liveState: LiveState,
    onStartLive: () -> Unit,
    onStopLive: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Live ($liveState)", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = onStartLive, modifier = Modifier.weight(1f)) { Text("Start Live") }
            Button(onClick = onStopLive, modifier = Modifier.weight(1f)) { Text("Stop Live") }
        }
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    useController = false
                    this.player = player
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                }
            },
            update = { view -> view.player = player }
        )
    }
}

@Composable
private fun Settings(settings: List<CameraSetting>, setValue: (String, String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Settings", style = MaterialTheme.typography.titleMedium)
        settings.forEach { setting ->
            val isToggle = setting.options.sorted() == listOf("off", "on") ||
                    setting.options.sorted() == listOf("OFF", "ON")

            if (isToggle) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(setting.key, style = MaterialTheme.typography.bodyMedium)
                    Switch(
                        checked = setting.value.equals("on", ignoreCase = true),
                        onCheckedChange = { checked ->
                            setValue(setting.key, if (checked) "on" else "off")
                        }
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(setting.key, style = MaterialTheme.typography.bodyMedium)
                    Box {
                        var expanded by remember { mutableStateOf(false) }
                        OutlinedButton(onClick = { expanded = true }) {
                            Text(setting.value)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            setting.options.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        expanded = false
                                        setValue(setting.key, option)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun fileToHttpUrl(path: String): String {
    return "http://192.168.42.1" + path.removePrefix("/tmp/fuse_d")
}

@Composable
private fun Files(
    files: List<CameraFile>,
    downloading: Map<String, Int>,
    refresh: () -> Unit,
    nextPage: () -> Unit,
    download: (CameraFile) -> Unit,
    httpClient: OkHttpClient
) {
    val context = LocalContext.current
    val imageLoader = remember(httpClient) {
        coil.ImageLoader.Builder(context)
            .okHttpClient(httpClient)
            .crossfade(true)
            .build()
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Files (${files.size})", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = refresh, modifier = Modifier.weight(1f)) { Text("Refresh") }
            Button(onClick = nextPage, modifier = Modifier.weight(1f)) { Text("Next page") }
        }

        files.chunked(2).forEach { rowFiles ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowFiles.forEach { file ->
                    val progress = downloading[file.name]
                    FileCard(
                        file = file,
                        progress = progress,
                        imageLoader = imageLoader,
                        onDownload = { download(file) },
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowFiles.size == 1) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun FileCard(
    file: CameraFile,
    progress: Int?,
    imageLoader: coil.ImageLoader,
    onDownload: () -> Unit,
    modifier: Modifier = Modifier
) {
    val thumbUrl = fileToHttpUrl(file.path) + "?type=thumb"

    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(6.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(thumbUrl)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = file.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
            Text(
                text = file.name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (progress != null) {
                LinearProgressIndicator(
                    progress = { progress / 100f },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("${progress}%", style = MaterialTheme.typography.labelSmall)
            } else {
                Button(
                    onClick = onDownload,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = ButtonDefaults.TextButtonContentPadding
                ) {
                    Text(if (file.downloaded) "Downloaded" else "Download", style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

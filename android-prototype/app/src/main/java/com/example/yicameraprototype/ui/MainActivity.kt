package com.example.yicameraprototype.ui

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.example.yicameraprototype.domain.CameraFile
import com.example.yicameraprototype.domain.CameraSetting
import com.example.yicameraprototype.domain.LiveState

class MainActivity : ComponentActivity() {
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
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
                        item { Text("Camera Control", style = MaterialTheme.typography.headlineSmall) }

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
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Button(onClick = vm::connect, modifier = Modifier.weight(1f)) { Text("Connect") }
                                Button(onClick = vm::disconnect, modifier = Modifier.weight(1f)) { Text("Disconnect") }
                            }
                        }

                        item { Text("Capture controls", style = MaterialTheme.typography.titleMedium) }
                        item {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Button(onClick = vm::startRecord, modifier = Modifier.weight(1f)) { Text("Start Rec") }
                                Button(onClick = vm::stopRecord, modifier = Modifier.weight(1f)) { Text("Stop Rec") }
                            }
                        }
                        item {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Button(onClick = vm::takePhoto, modifier = Modifier.weight(1f)) { Text("Photo") }
                                Button(onClick = vm::pollRecordStatus, modifier = Modifier.weight(1f)) { Text("Poll Rec") }
                                Button(onClick = vm::exportLogs, modifier = Modifier.weight(1f)) { Text("Export Logs") }
                            }
                        }

                        item {
                            LivePlayer(
                                onState = { st, reason -> vm.updateLiveState(st, reason) },
                                onManualStop = { vm.updateLiveState(LiveState.Stopped, "manual") }
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
                                open = vm::open,
                                share = vm::share
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
    onState: (LiveState, String?) -> Unit,
    onManualStop: () -> Unit
) {
    val context = LocalContext.current
    val controller = remember { LiveStreamController(context) }

    DisposableEffect(Unit) {
        controller.setStateListener(onState)
        onDispose { controller.release() }
    }

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Live", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { controller.start() }, modifier = Modifier.weight(1f)) { Text("Start Live") }
            Button(onClick = {
                controller.stop()
                onManualStop()
            }, modifier = Modifier.weight(1f)) { Text("Stop Live") }
        }
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    useController = true
                    player = controller.player
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                }
            }
        )
    }
}

@Composable
private fun Settings(settings: List<CameraSetting>, setValue: (String, String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Settings", style = MaterialTheme.typography.titleMedium)
        settings.forEach { setting ->
            Text("${setting.key}=${setting.value}")
            setting.options.chunked(3).forEach { rowOptions ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                    rowOptions.forEach { option ->
                        Button(onClick = { setValue(setting.key, option) }, modifier = Modifier.weight(1f)) { Text(option) }
                    }
                    repeat(3 - rowOptions.size) {
                        Column(modifier = Modifier.weight(1f)) {}
                    }
                }
            }
        }
    }
}

@Composable
private fun Files(
    files: List<CameraFile>,
    downloading: Map<String, Int>,
    refresh: () -> Unit,
    nextPage: () -> Unit,
    download: (CameraFile) -> Unit,
    open: (CameraFile) -> Unit,
    share: (CameraFile) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Files", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = refresh, modifier = Modifier.weight(1f)) { Text("Refresh") }
            Button(onClick = nextPage, modifier = Modifier.weight(1f)) { Text("Next page") }
        }

        files.take(20).forEach { file ->
            val progress = downloading[file.name]
            val progressText = progress?.let { " • dl=${it}%" } ?: ""
            val dateText = file.date?.let { " • $it" } ?: ""
            Text("${file.name} (${file.size}) • ${file.type}$dateText$progressText")

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { download(file) }, modifier = Modifier.weight(1f)) { Text("Download") }
                Button(onClick = { open(file) }, modifier = Modifier.weight(1f)) { Text("Open") }
                Button(onClick = { share(file) }, modifier = Modifier.weight(1f)) { Text("Share") }
            }
        }
    }
}

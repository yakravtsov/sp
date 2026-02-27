package com.example.yicameraprototype.ui

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Connection: ${state.connectionState}")
                    Text("Model: ${state.model}, token=${state.token ?: "-"}")
                    Text("Live: ${state.liveState}, recording=${state.isRecording}")
                    Text("Battery: ${state.batteryPercent?.toString() ?: "-"}% , adapter=${state.adapterConnected ?: "-"}")
                    Text("Camera status: sd=${state.sdCardStatus ?: "-"}, app=${state.appStatus ?: "-"}")
                    Text("Flags: live=${state.sessionFlags.live}, sdformat=${state.sessionFlags.sdformat}, album=${state.sessionFlags.album}")

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = vm::connect) { Text("Connect") }
                        Button(onClick = vm::disconnect) { Text("Disconnect") }
                        Button(onClick = vm::startRecord) { Text("Start Rec") }
                        Button(onClick = vm::stopRecord) { Text("Stop Rec") }
                        Button(onClick = vm::takePhoto) { Text("Photo") }
                    }

                    LivePlayer(
                        onState = { st, reason -> vm.updateLiveState(st, reason) },
                        onManualStop = { vm.updateLiveState(LiveState.Stopped, "manual") }
                    )

                    Settings(state.settings, vm::setSetting)
                    Files(
                        files = state.files,
                        downloading = state.downloading,
                        refresh = vm::refreshFiles,
                        nextPage = vm::refreshFilesNextPage,
                        download = vm::download,
                        open = vm::open,
                        share = vm::share
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = vm::pollRecordStatus) { Text("Poll Rec Status") }
                        Button(onClick = vm::exportLogs) { Text("Export Logs") }
                    }

                    Text("Tech log")
                    LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
                        items(state.logs) { line ->
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
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { controller.start() }) { Text("Start Live") }
            Button(onClick = {
                controller.stop()
                onManualStop()
            }) { Text("Stop Live") }
        }
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    useController = true
                    player = controller.player
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 480)
                }
            }
        )
    }
}

@Composable
private fun Settings(settings: List<CameraSetting>, setValue: (String, String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Settings")
        settings.forEach { setting ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("${setting.key}=${setting.value}", modifier = Modifier.weight(1f))
                setting.options.take(3).forEach { option ->
                    Button(onClick = { setValue(setting.key, option) }) { Text(option) }
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
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Files")
            Button(onClick = refresh) { Text("Refresh") }
            Button(onClick = nextPage) { Text("Next page") }
        }
        files.take(20).forEach { file ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val progress = downloading[file.name]
                val progressText = progress?.let { " • dl=${it}%" } ?: ""
                val dateText = file.date?.let { " • $it" } ?: ""
                Text("${file.name} (${file.size}) • ${file.type}$dateText$progressText", modifier = Modifier.weight(1f))
                Button(onClick = { download(file) }) { Text("Download") }
                Button(onClick = { open(file) }) { Text("Open") }
                Button(onClick = { share(file) }) { Text("Share") }
            }
        }
    }
}

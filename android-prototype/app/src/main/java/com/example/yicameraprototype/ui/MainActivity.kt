package com.example.yicameraprototype.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.example.yicameraprototype.domain.CameraSetting
import com.example.yicameraprototype.domain.LiveState

class MainActivity : ComponentActivity() {
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val state by vm.uiState.collectAsState()
                Column(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Connection: ${state.connectionState}")
                    Text("Model: ${state.model}, token=${state.token ?: "-"}")
                    Text("Live: ${state.liveState}")
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = vm::connect) { Text("Connect") }
                        Button(onClick = vm::disconnect) { Text("Disconnect") }
                        Button(onClick = vm::startRecord) { Text("Start Rec") }
                        Button(onClick = vm::stopRecord) { Text("Stop Rec") }
                        Button(onClick = vm::takePhoto) { Text("Photo") }
                    }
                    LivePlayer(
                        onState = { vm.updateLiveState(it) },
                        onError = { vm.updateLiveState(LiveState.Error, it) }
                    )
                    Settings(state.settings, vm::setSetting)
                    Files(state.files.map { it.name }, vm::refreshFiles)
                    Text("Tech log")
                    LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
                        items(state.logs) { line -> Text(line) }
                    }
                }
            }
        }
    }
}

@Composable
private fun LivePlayer(
    onState: (LiveState) -> Unit,
    onError: (String) -> Unit
) {
    val context = LocalContext.current
    val controller = LiveStreamController(context)
    DisposableEffect(Unit) {
        onDispose { controller.release() }
    }

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                onState(LiveState.Buffering)
                runCatching { controller.start() }
                    .onSuccess { onState(LiveState.Playing) }
                    .onFailure { onError(it.message ?: "Live error") }
            }) { Text("Start Live") }
            Button(onClick = {
                controller.stop()
                onState(LiveState.Stopped)
            }) { Text("Stop Live") }
        }
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    useController = true
                    player = controller.player
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        480
                    )
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
                Text(setting.key, modifier = Modifier.weight(1f))
                setting.options.take(3).forEach { option ->
                    Button(onClick = { setValue(setting.key, option) }) { Text(option) }
                }
            }
        }
    }
}

@Composable
private fun Files(files: List<String>, refresh: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Files")
            Button(onClick = refresh) { Text("Refresh") }
        }
        files.take(10).forEach { Text(it) }
    }
}

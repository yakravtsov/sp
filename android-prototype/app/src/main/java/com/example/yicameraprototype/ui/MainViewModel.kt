package com.example.yicameraprototype.ui

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.example.yicameraprototype.data.camera.CameraRepository
import com.example.yicameraprototype.domain.CameraFile
import com.example.yicameraprototype.domain.CameraUiState
import com.example.yicameraprototype.domain.ConnectionState
import com.example.yicameraprototype.domain.LiveState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class MainViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "ViewModel"
    }
    private val repository = CameraRepository(application.applicationContext)
    val uiState: StateFlow<CameraUiState> = repository.uiState

    private val liveController = LiveStreamController(application.applicationContext)
    val player: ExoPlayer get() = liveController.player
    val cameraHttpClient: OkHttpClient get() = repository.httpClient

    private val _oneShotIntents = MutableSharedFlow<Intent>(extraBufferCapacity = 8)
    val oneShotIntents: SharedFlow<Intent> = _oneShotIntents

    init {
        liveController.setStateListener { state, reason -> updateLiveState(state, reason) }

        // Auto-start live when CameraReady
        viewModelScope.launch {
            uiState.map { it.connectionState }
                .distinctUntilChanged()
                .collect { connState ->
                    if (connState == ConnectionState.CameraReady) {
                        Log.d(TAG, "Auto-starting live (CameraReady)")
                        liveController.start()
                    } else if (connState == ConnectionState.Disconnected || connState == ConnectionState.Error) {
                        liveController.stop("connection lost")
                    }
                }
        }
    }

    fun connect() = viewModelScope.launch {
        Log.d(TAG, ">> connect")
        runCatching { repository.connect() }
            .onFailure { repository.reportConnectionError(it) }
    }
    fun disconnect() = viewModelScope.launch {
        Log.d(TAG, ">> disconnect")
        repository.disconnect()
    }
    fun reconnect(reason: String) = viewModelScope.launch {
        Log.d(TAG, ">> reconnect reason=$reason")
        repository.reconnect(reason)
    }

    fun setSetting(key: String, value: String) = viewModelScope.launch {
        Log.d(TAG, ">> setSetting key=$key value=$value")
        repository.changeSetting(key, value)
        // Restart live after setting change (VF was restarted in changeSetting)
        if (uiState.value.connectionState == ConnectionState.CameraReady) {
            Log.d(TAG, "Restarting live after setting change")
            kotlinx.coroutines.delay(500) // wait for vf_start
            liveController.start()
        }
    }
    fun refreshFiles() = viewModelScope.launch {
        Log.d(TAG, ">> refreshFiles")
        repository.refreshFiles(uiState.value.fileWindowFrom, uiState.value.fileWindowTo)
    }
    fun refreshFilesNextPage() = viewModelScope.launch {
        val from = uiState.value.fileWindowTo
        repository.refreshFiles(from, from + 60)
    }

    fun download(file: CameraFile) = viewModelScope.launch {
        Log.d(TAG, ">> download file=${file.name}")
        liveController.stop("download")
        repository.download(file)
        // VF restarted inside repository.download(); restart live stream
        if (uiState.value.connectionState == ConnectionState.CameraReady) {
            kotlinx.coroutines.delay(500)
            liveController.start()
        }
    }

    fun startRecord() = viewModelScope.launch {
        Log.d(TAG, ">> startRecord")
        repository.startRecord()
    }
    fun stopRecord() = viewModelScope.launch {
        Log.d(TAG, ">> stopRecord")
        repository.stopRecord()
    }
    fun takePhoto() = viewModelScope.launch {
        Log.d(TAG, ">> takePhoto")
        repository.takePhoto()
    }
    fun pollRecordStatus() = viewModelScope.launch {
        Log.d(TAG, ">> pollRecordStatus")
        repository.pollRecordStatus()
    }

    fun exportLogs() = _oneShotIntents.tryEmit(
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_STREAM, repository.exportDebugLog())
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    )

    fun startLive() {
        Log.d(TAG, ">> startLive")
        liveController.start()
    }

    fun stopLive() {
        Log.d(TAG, ">> stopLive")
        liveController.stop()
        updateLiveState(LiveState.Stopped, "manual")
    }

    fun updateLiveState(state: LiveState, reason: String? = null) {
        Log.d(TAG, ">> updateLiveState state=$state reason=$reason")
        repository.onLiveStateChanged(state, reason)
    }

    override fun onCleared() {
        super.onCleared()
        liveController.release()
    }
}

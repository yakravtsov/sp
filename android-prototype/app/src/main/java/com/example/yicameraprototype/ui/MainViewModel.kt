package com.example.yicameraprototype.ui

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.yicameraprototype.data.camera.CameraRepository
import com.example.yicameraprototype.domain.CameraFile
import com.example.yicameraprototype.domain.CameraUiState
import com.example.yicameraprototype.domain.LiveState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CameraRepository(application.applicationContext)
    val uiState: StateFlow<CameraUiState> = repository.uiState

    private val _oneShotIntents = MutableSharedFlow<Intent>(extraBufferCapacity = 8)
    val oneShotIntents: SharedFlow<Intent> = _oneShotIntents

    fun connect() = viewModelScope.launch { repository.connect() }
    fun disconnect() = viewModelScope.launch { repository.disconnect() }
    fun reconnect(reason: String) = viewModelScope.launch { repository.reconnect(reason) }

    fun setSetting(key: String, value: String) = viewModelScope.launch { repository.changeSetting(key, value) }
    fun refreshFiles() = viewModelScope.launch { repository.refreshFiles(uiState.value.fileWindowFrom, uiState.value.fileWindowTo) }
    fun refreshFilesNextPage() = viewModelScope.launch {
        val from = uiState.value.fileWindowTo
        repository.refreshFiles(from, from + 60)
    }

    fun download(file: CameraFile) = viewModelScope.launch { repository.download(file) }
    fun open(file: CameraFile) = _oneShotIntents.tryEmit(repository.open(file))
    fun share(file: CameraFile) = _oneShotIntents.tryEmit(repository.share(file))

    fun startRecord() = viewModelScope.launch { repository.startRecord() }
    fun stopRecord() = viewModelScope.launch { repository.stopRecord() }
    fun takePhoto() = viewModelScope.launch { repository.takePhoto() }
    fun pollRecordStatus() = viewModelScope.launch { repository.pollRecordStatus() }

    fun exportLogs() = _oneShotIntents.tryEmit(
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_STREAM, repository.exportDebugLog())
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    )

    fun updateLiveState(state: LiveState, reason: String? = null) {
        repository.onLiveStateChanged(state, reason)
        if (state == LiveState.Error) reconnect(reason ?: "live error")
    }
}

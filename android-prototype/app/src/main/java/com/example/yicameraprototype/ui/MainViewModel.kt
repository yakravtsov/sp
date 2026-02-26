package com.example.yicameraprototype.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.yicameraprototype.data.camera.CameraRepository
import com.example.yicameraprototype.domain.CameraFile
import com.example.yicameraprototype.domain.LiveState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CameraRepository(application.applicationContext)
    val uiState: StateFlow<com.example.yicameraprototype.domain.CameraUiState> = repository.uiState

    fun connect() = viewModelScope.launch { repository.connect() }
    fun disconnect() = viewModelScope.launch { repository.disconnect() }
    fun setSetting(key: String, value: String) = viewModelScope.launch { repository.changeSetting(key, value) }
    fun refreshFiles() = viewModelScope.launch { repository.refreshFiles() }
    fun download(file: CameraFile) = viewModelScope.launch { repository.download(file) }
    fun startRecord() = viewModelScope.launch { repository.startRecord() }
    fun stopRecord() = viewModelScope.launch { repository.stopRecord() }
    fun takePhoto() = viewModelScope.launch { repository.takePhoto() }

    fun updateLiveState(state: LiveState, reason: String? = null) {
        repository.onLiveStateChanged(state, reason)
    }
}

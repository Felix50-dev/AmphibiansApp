package com.example.amphibiansapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansapp.AmphibiansApplication
import com.example.amphibiansapp.data.AmphibiansRepository
import com.example.amphibiansapp.model.Amphibian
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}

class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {

    /** The mutable State that stores the status of the most recent request */
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API
     */
    private fun getMarsPhotos() {
        viewModelScope.launch {
            amphibianUiState = try {
                AmphibianUiState.Success(amphibiansRepository.getAmphibians())
            } catch (e: IOException) {
                AmphibianUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}
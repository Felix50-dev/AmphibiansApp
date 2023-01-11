package com.example.amphibiansapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibiansapp.AmphibiansApi
import com.example.amphibiansapp.model.Amphibian
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}

class AmphibiansViewModel : ViewModel() {

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
                val listResult = AmphibiansApi.retrofitService.getAmphibians()
                AmphibianUiState.Success(listResult)
            } catch (e: IOException) {
                AmphibianUiState.Error
            }
        }
    }
}
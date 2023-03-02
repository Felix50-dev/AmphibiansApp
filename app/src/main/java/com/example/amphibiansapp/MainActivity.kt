package com.example.amphibiansapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.amphibiansapp.ui.screens.AmphibiansViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibiansapp.ui.screens.HomeScreen
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiansAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val amphibiansViewModel : AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
                    HomeScreen(
                        amphibianUiState = amphibiansViewModel.amphibianUiState
                    )
                }
            }
        }
    }
}

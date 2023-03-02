package com.example.amphibiansapp.fake

import com.example.amphibiansapp.rules.TestDispatcherRule
import com.example.amphibiansapp.ui.screens.AmphibianUiState
import com.example.amphibiansapp.ui.screens.AmphibiansViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AmphibiansViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest {
            val amphibiansViewModel = AmphibiansViewModel(
                amphibiansRepository =  FakeAmphibiansRepository()
            )
            assertEquals(
                AmphibianUiState.Success(FakeDataSource.amphibiansList),
                amphibiansViewModel.amphibianUiState
            )
        }
}


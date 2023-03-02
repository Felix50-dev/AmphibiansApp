package com.example.amphibiansapp.fake

import com.example.amphibiansapp.data.AmphibiansRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AmphibiansRepositoryTest {

    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() =
        runTest {
            val repository = AmphibiansRepository.DefaultAmphibiansRepository(
                amphibiansApiService = FakeApiService()
            )
            assertEquals(FakeDataSource.amphibiansList, repository.getAmphibians())
        }
}
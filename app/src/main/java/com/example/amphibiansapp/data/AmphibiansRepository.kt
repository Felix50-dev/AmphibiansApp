package com.example.amphibiansapp.data

import com.example.amphibiansapp.AmphibiansApiService
import com.example.amphibiansapp.model.Amphibian

interface AmphibiansRepository {

    suspend fun getAmphibians(): List<Amphibian>

    class DefaultAmphibiansRepository(
        private val amphibiansApiService: AmphibiansApiService
    ) : AmphibiansRepository {
        override suspend fun getAmphibians(): List<Amphibian> {
            return amphibiansApiService.getAmphibians()
        }

    }
}
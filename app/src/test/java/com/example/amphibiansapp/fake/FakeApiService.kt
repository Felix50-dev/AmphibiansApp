package com.example.amphibiansapp.fake

import com.example.amphibiansapp.AmphibiansApiService
import com.example.amphibiansapp.model.Amphibian

class FakeApiService: AmphibiansApiService {
    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeDataSource.amphibiansList
    }
}
package com.example.amphibiansapp

import com.example.amphibiansapp.model.Amphibian
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


interface AmphibiansApiService {

    /**
     * Returns a [List] of [Amphibian] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "amphibians" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>

}
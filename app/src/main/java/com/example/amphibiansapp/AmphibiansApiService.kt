package com.example.amphibiansapp

import com.example.amphibiansapp.model.Amphibian
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
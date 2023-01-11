package com.example.amphibiansapp

import com.example.amphibiansapp.model.Amphibian
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = " https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface AmphibiansApiService {

    /**
     * Returns a [List] of [Amphibian] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "amphibians" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>

}

object AmphibiansApi {
    val retrofitService : AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }
}
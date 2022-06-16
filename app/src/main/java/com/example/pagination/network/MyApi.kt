package com.example.pagination.network

import com.example.pagination.BuildConfig
import com.example.pagination.data.local.Movies
import com.example.pagination.network.MyApi.Companion.API_KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApi {

    @GET("now_playing?api_key=$API_KEY&=en-US")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Movies

    companion object {
        const val API_KEY = BuildConfig.API_KEY

        operator fun invoke(): MyApi = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
}

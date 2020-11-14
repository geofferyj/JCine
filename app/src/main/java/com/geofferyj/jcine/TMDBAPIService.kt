package com.geofferyj.jcine

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPIService {


    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String = "270d85f12115c58ce7ba9759eb8f8dbe"):Response<MovieResult>



    companion object{
        private val retrofit by lazy{
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService by lazy {
            retrofit.create(TMDBAPIService::class.java)
        }
    }


}
package com.geofferyj.jcine.models.api

import com.geofferyj.jcine.models.MovieDetails
import com.geofferyj.jcine.models.MovieModel
import com.geofferyj.jcine.utils.Constants.Companion.API_KEY
import com.geofferyj.jcine.utils.Constants.Companion.LANG
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

// ************* Movie Requests Start *******************//

    @GET("movie/upcoming")
    suspend fun getMovieComingSoon(
        @Query("api_key") ApiKey: String = API_KEY,
        @Query("language") lang: String = LANG
    ): Response<MovieModel>


    @GET("trending/movie/week")
    suspend fun getMovieTrending(@Query("api_key") ApiKey: String = API_KEY): Response<MovieModel>


    @GET("movie/now_playing")
    suspend fun getMovieNewRelease(
        @Query("api_key") ApiKey: String = API_KEY,
        @Query("language") lang: String = LANG
    ): Response<MovieModel>


    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") ApiKey: String = API_KEY,
        @Query("language") lang: String = LANG,
        @Query("append_to_response") cast: String = "credits"
    ): Response<MovieDetails>


    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key") ApiKey: String = API_KEY,
        @Query("language") lang: String = LANG
    ): Response<MovieModel>



    // ************* TV Series Requests Start *******************//
    @GET("tv/popular")
    suspend fun getTVPopular(
        @Query("api_key") ApiKey: String = API_KEY,
        @Query("language") lang: String = LANG,
        @Query("page") page: Int = 3
    ): Response<MovieModel>

    @GET("tv/popular")
    suspend fun getTVComingSoon(
        @Query("api_key") ApiKey: String = API_KEY,
        @Query("language") lang: String = LANG,
        @Query("page") page: Int = 1
    ): Response<MovieModel>


    @GET("tv/popular")
    suspend fun getTVLatest(
        @Query("api_key") ApiKey: String = API_KEY,
        @Query("language") lang: String = LANG,
        @Query("page") page: Int = 2
    ): Response<MovieModel>

    @GET("tv/{id}")
    suspend fun getTvDetails(
        @Path("id") id: Int,
        @Query("api_key") ApiKey: String = API_KEY,
        @Query("language") lang: String = LANG,
        @Query("append_to_response") cast: String = "aggregate_credits"
    ): Response<MovieDetails>
}
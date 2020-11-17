package com.geofferyj.jcine.models.repository


import com.geofferyj.jcine.models.api.RetrofitInstance

class Repository {

private val service = RetrofitInstance.api
    suspend fun getMovieComingSoon() = service.getMovieComingSoon()
    suspend fun getMovieTrending() = service.getMovieTrending()
    suspend fun getMovieDetails(id:Int) = service.getMovieDetails(id)
    suspend fun getMovieNewRelease() = service.getMovieNewRelease()
    suspend fun getMoviePopular() = service.getMoviePopular()



    suspend fun getTvPopular() = service.getTVPopular()
    suspend fun getTvComingSoon() = service.getTVComingSoon()
    suspend fun getTvNewRelease() = service.getTVLatest()
    suspend fun getTvDetails(id: Int) = service.getTvDetails(id)

}
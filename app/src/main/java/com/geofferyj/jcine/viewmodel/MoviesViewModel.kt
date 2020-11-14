package com.geofferyj.jcine.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.geofferyj.jcine.models.MovieDetails
import com.geofferyj.jcine.models.MovieModel
import com.geofferyj.jcine.models.repository.Repository
import com.geofferyj.jcine.utils.Resource
import kotlinx.coroutines.*
import retrofit2.Response

class MoviesViewModel(application: Application, val repository: Repository) :
    AndroidViewModel(application) {

    val moviesComingSoon: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val movieDetails: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()
    val moviesPopular: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val moviesNewRelease: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val moviesTrending: MutableLiveData<Resource<MovieModel>> = MutableLiveData()


    val tvPopular: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val tvNewRelease: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val tvComingSoon: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val tvDetails: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()

    init {

        getMovieComingSoon()
        getMovieNewRelease()
        getMoviePopular()
        getMovieTrending()
        getTVPopular()
        getTVComingSoon()
        getTVNewRelease()
    }

    fun getMovieComingSoon() = viewModelScope.launch(Dispatchers.IO) {

        moviesComingSoon.postValue(Resource.Loading())
        val response = repository.getMovieComingSoon()
        moviesComingSoon.postValue(handleResponse(response))

    }


    fun getMoviePopular() = viewModelScope.launch(Dispatchers.IO) {

        moviesPopular.postValue(Resource.Loading())
        val response = repository.getMoviePopular()
        moviesPopular.postValue(handleResponse(response))
    }




    fun getMovieTrending() = viewModelScope.launch(Dispatchers.IO) {

        moviesTrending.postValue(Resource.Loading())
        val response = repository.getMovieTrending()
        moviesTrending.postValue(handleResponse(response))

    }

    fun getMovieNewRelease() = viewModelScope.launch(Dispatchers.IO) {

        moviesNewRelease.postValue(Resource.Loading())
        val response = repository.getMovieNewRelease()
        moviesNewRelease.postValue(handleResponse(response))

    }




    fun getTVPopular() = viewModelScope.launch(Dispatchers.IO) {

        tvPopular.postValue(Resource.Loading())
        val response = repository.getTvPopular()
        tvPopular.postValue(handleResponse(response))
    }


    fun getTVNewRelease() = viewModelScope.launch(Dispatchers.IO) {

        tvNewRelease.postValue(Resource.Loading())
        val response = repository.getTvNewRelease()
        tvNewRelease.postValue(handleResponse(response))
    }


    fun getTVComingSoon() = viewModelScope.launch(Dispatchers.IO) {

        tvComingSoon.postValue(Resource.Loading())
        val response = repository.getTvComingSoon()
        tvComingSoon.postValue(handleResponse(response))
    }


    fun getTVDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {

        tvDetails.postValue(Resource.Loading())
        val response = repository.getTVDetails(id)
        tvDetails.postValue(handleResponse(response))
    }




    fun <T> handleResponse(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

}
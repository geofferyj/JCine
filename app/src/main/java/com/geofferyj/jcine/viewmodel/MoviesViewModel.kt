package com.geofferyj.jcine.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.geofferyj.jcine.models.MovieDetails
import com.geofferyj.jcine.models.MovieModel
import com.geofferyj.jcine.models.repository.Repository
import com.geofferyj.jcine.utils.NetworkConnection
import com.geofferyj.jcine.utils.NoInternetException
import com.geofferyj.jcine.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel(val app: Application, val repository: Repository) :
    AndroidViewModel(app) {

    val moviesComingSoon: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val movieDetails: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()
    val moviesPopular: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val moviesNewRelease: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val moviesTrending: MutableLiveData<Resource<MovieModel>> = MutableLiveData()


    val tvPopular: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val tvNewRelease: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val tvComingSoon: MutableLiveData<Resource<MovieModel>> = MutableLiveData()
    val tvDetails: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()


    val networkConnection = NetworkConnection(app.applicationContext)


    init {

        networkConnection.observeForever {
            Log.d("Observer Forever check", this.toString())
            if (it) {
                getMovieComingSoon()
                getMovieNewRelease()
                getMoviePopular()
                getMovieTrending()
                getTVPopular()
                getTVComingSoon()
                getTVNewRelease()
            } else {
                moviesComingSoon.postValue(Resource.NetworkError("No Internet Connection"))
                movieDetails.postValue(Resource.NetworkError("No Internet Connection"))
                moviesPopular.postValue(Resource.NetworkError("No Internet Connection"))
                moviesNewRelease.postValue(Resource.NetworkError("No Internet Connection"))
                moviesTrending.postValue(Resource.NetworkError("No Internet Connection"))
                tvPopular.postValue(Resource.NetworkError("No Internet Connection"))
                tvNewRelease.postValue(Resource.NetworkError("No Internet Connection"))
                tvComingSoon.postValue(Resource.NetworkError("No Internet Connection"))
                tvDetails.postValue(Resource.NetworkError("No Internet Connection"))

            }
        }

    }


    private fun getMovieComingSoon() = viewModelScope.launch(Dispatchers.IO) {

        moviesComingSoon.postValue(Resource.Loading())
        val response = repository.getMovieComingSoon()
        moviesComingSoon.postValue(handleResponse(response))

    }


    private fun getMoviePopular() = viewModelScope.launch(Dispatchers.IO) {

        moviesPopular.postValue(Resource.Loading())
        val response = repository.getMoviePopular()
        moviesPopular.postValue(handleResponse(response))

    }


    private fun getMovieTrending() = viewModelScope.launch(Dispatchers.IO) {


        moviesTrending.postValue(Resource.Loading())
        val response = repository.getMovieTrending()
        moviesTrending.postValue(handleResponse(response))

    }

    private fun getMovieNewRelease() = viewModelScope.launch(Dispatchers.IO) {

        moviesNewRelease.postValue(Resource.Loading())
        val response = repository.getMovieNewRelease()
        moviesNewRelease.postValue(handleResponse(response))

    }

    fun getDetails(id: Int, type:String) = GlobalScope.launch(Dispatchers.IO) {
        if (type=="tv"){
            tvDetails.postValue(Resource.Loading())
            val response = Repository().getTvDetails(id)
            tvDetails.postValue(handleResponse(response))
        }else{

            movieDetails.postValue(Resource.Loading())
            val response = Repository().getMovieDetails(id)
            movieDetails.postValue(handleResponse(response))
        }
    }


    private fun getTVPopular() = viewModelScope.launch(Dispatchers.IO) {

        tvPopular.postValue(Resource.Loading())
        val response = repository.getTvPopular()
        tvPopular.postValue(handleResponse(response))

    }


    private fun getTVNewRelease() = viewModelScope.launch(Dispatchers.IO) {

        tvNewRelease.postValue(Resource.Loading())
        val response = repository.getTvNewRelease()
        tvNewRelease.postValue(handleResponse(response))

    }


    private fun getTVComingSoon() = viewModelScope.launch(Dispatchers.IO) {

        tvComingSoon.postValue(Resource.Loading())
        val response = repository.getTvComingSoon()
        tvComingSoon.postValue(handleResponse(response))

    }


    private fun <T> handleResponse(response: Response<T>): Resource<T> {

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())

    }

    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }


}
package com.geofferyj.jcine.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data)
    class Loading<T> : Resource<T>()
    class NetworkError<T>(message: String) : Resource<T>(message = message)

}
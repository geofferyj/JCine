package com.geofferyj.jcine.models


import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("results")
    val movies: List<Movie>
){
    data class Movie(

        @SerializedName("id")
        val id: Int,

        @SerializedName("poster_path")
        val posterPath: String,

        @SerializedName("backdrop_path")
        val backdropPath: String,

        @SerializedName(value = "title", alternate = ["name"])
        val title: String,

        )
}
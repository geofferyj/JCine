package com.geofferyj.jcine


import com.google.gson.annotations.SerializedName

data class Result(


    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String
)
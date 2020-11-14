package com.geofferyj.jcine

import com.google.gson.annotations.SerializedName

data class MovieInfo(


    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,

    val backDropPath: String,
    val overview: String
)


package com.geofferyj.jcine


import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("results")
    val results: List<Result>
)
package com.geofferyj.jcine.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("genres")
    val genres: List<Genre>,
    
    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("credits")
    val credits: Credits,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,

    @SerializedName("vote_average")
    val rating: Float
): Parcelable {
    @Parcelize
    data class Genre(
        @SerializedName("name")
        val name: String
    ):Parcelable

    @Parcelize
    data class SpokenLanguage(
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("name")
        val name: String
    ):Parcelable

    @Parcelize
    data class Credits(
        @SerializedName("cast")
        val cast: List<Cast>,

    ):Parcelable {

        @Parcelize
        data class Cast(

            @SerializedName("name")
            val name: String?,

            @SerializedName("profile_path")
            val profilePath: String?
        ):Parcelable


    }
}
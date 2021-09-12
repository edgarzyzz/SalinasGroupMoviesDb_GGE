package com.gogaedd.salinasgroupmoviedb_gge.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Movie(

    @PrimaryKey
    @SerializedName("id")
    var id: String = "",

    @SerializedName("adult")
    var isMovieAdult: String = "",

    @SerializedName("backdrop_path")
    var coverHor: String = "",

    @SerializedName("original_language")
    var originalLanguage: String = "",

    @SerializedName("original_title")
    var originalTitle: String = "",

    @SerializedName("overview")
    var overview: String = "",

    @SerializedName("popularity")
    var popularity: String = "",

    @SerializedName("poster_path")
    var coverVert: String = "",

    @SerializedName("release_date")
    var releaseDate: String = "",

    @SerializedName("title")
    var title: String = "",

    @SerializedName("video")
    var video: String = "",

    @SerializedName("vote_average")
    var voteAverage: String = "",

    @SerializedName("vote_count")
    var voteCount: String = ""


)

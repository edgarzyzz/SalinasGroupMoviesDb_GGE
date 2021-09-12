package com.gogaedd.salinasgroupmoviedb_gge.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    var page: Int=0,

    @SerializedName("results")
    var listMovies: MutableList<Movie> = mutableListOf()

)

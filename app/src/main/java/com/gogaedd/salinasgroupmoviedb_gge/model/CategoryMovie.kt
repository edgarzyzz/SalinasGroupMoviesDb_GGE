package com.gogaedd.salinasgroupmoviedb_gge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryMovie(
    @PrimaryKey
    val id:String= "",
    var movie_now_playing :Int = 0,
    var movie_popular :Int = 0
)

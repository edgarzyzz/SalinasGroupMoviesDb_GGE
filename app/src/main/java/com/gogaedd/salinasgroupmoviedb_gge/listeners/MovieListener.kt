package com.gogaedd.salinasgroupmoviedb_gge.listeners

import com.gogaedd.salinasgroupmoviedb_gge.model.Movie

interface MovieListener {
    fun onClickMovie(moview: Movie)
}
package com.gogaedd.salinasgroupmoviedb_gge.infraestructure

object ConstantsApp {

    const val WS_END_POINT = "3/movie/"
    const val WS_API_KEY = "45bf6592c14a965b33549f4cc7e6c664"
    object NET{
        const val WS_MOST_POPULAR_GET = 1001
        const val WS_NOW_PLAYING_GET = 2001
    }

    object STATE_LOAD{
        const val START_LOADING = 1
        const val LOAD_ERROR = 2
        const val LOAD_OK = 3

    }
}
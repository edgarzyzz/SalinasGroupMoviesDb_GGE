package com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.net

import com.gogaedd.salinasgroupmoviedb_gge.infraestructure.ConstantsApp
import com.gogaedd.salinasgroupmoviedb_gge.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("${ConstantsApp.WS_END_POINT}popular")
    fun getPopularMovies(@Query("api_key")apiKey:String): Call<MoviesResponse>

    @GET("${ConstantsApp.WS_END_POINT}now_playing")
    fun getNowPlaying(@Query("api_key")apiKey:String): Call<MoviesResponse>



}
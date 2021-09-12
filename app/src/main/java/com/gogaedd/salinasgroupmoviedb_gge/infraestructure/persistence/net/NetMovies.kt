package com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.net

import com.gogaedd.salinasgroupmoviedb_gge.infraestructure.ConstantsApp
import com.gogaedd.salinasgroupmoviedb_gge.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetMovies (): NetBaseHelper(){

    fun sendRequestMostPopular(){
        val call =  apiService.getPopularMovies(ConstantsApp.WS_API_KEY)
        call.enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        val listMovies = it.listMovies
                        mListener?.onResultOk(listMovies,ConstantsApp.NET.WS_MOST_POPULAR_GET)
                        return
                    }


                }

                mListener?.onResultError("response error", ConstantsApp.NET.WS_MOST_POPULAR_GET)

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                mListener?.onResultError(" ${t.message}", ConstantsApp.NET.WS_MOST_POPULAR_GET)
            }
        })
    }

    fun sendRequestNowPlaying(){

        val call = apiService.getNowPlaying(ConstantsApp.WS_API_KEY)
        call.enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        val listMovies = it.listMovies
                        mListener?.onResultOk(listMovies,ConstantsApp.NET.WS_NOW_PLAYING_GET)
                        return
                    }


                }

                mListener?.onResultError("response error", ConstantsApp.NET.WS_NOW_PLAYING_GET)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                mListener?.onResultError(" ${t.message}", ConstantsApp.NET.WS_NOW_PLAYING_GET)
            }
        })
    }
}
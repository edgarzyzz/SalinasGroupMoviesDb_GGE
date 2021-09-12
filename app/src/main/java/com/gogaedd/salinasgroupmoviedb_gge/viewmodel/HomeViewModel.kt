package com.gogaedd.salinasgroupmoviedb_gge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gogaedd.salinasgroupmoviedb_gge.model.Movie
import com.gogaedd.salinasgroupmoviedb_gge.repository.HomeRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val repository =  HomeRepository(application)

    private val lvdPopularMovies = repository.getLvdPopularMovies()
    private val lvdPlayingMovies = repository.getLvdPlayingMovies()

    fun getLvdPopularMovies() = lvdPopularMovies
    fun getLvdPlayingMovies() = lvdPlayingMovies


    fun startLoad(){
        repository.loadPopularMovies()
    }





}
package com.gogaedd.salinasgroupmoviedb_gge.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.db.AppDatabase
import com.gogaedd.salinasgroupmoviedb_gge.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class HomeRepository(application: Application) {
    private val db = AppDatabase.getDatabase(application)
    private val movieDao = db.movieDao()
    private val categoryMoviesDao = db.categoryMovieDao()

    private val lvdPopularMovies = MutableLiveData<MutableList<Movie>>(mutableListOf())
    private val lvdPlayingMovies = MutableLiveData<MutableList<Movie>>(mutableListOf())

    fun getLvdPopularMovies() = lvdPopularMovies
    fun getLvdPlayingMovies() = lvdPlayingMovies


    fun loadPopularMovies(){
        runBlocking(Dispatchers.IO) {
            val listIdPularMovies = categoryMoviesDao.getListIdPularMovies()
            val moviesById = movieDao.getMoviesById(listIdPularMovies)
            lvdPopularMovies.postValue(moviesById)
            loadPlayingMovies()
        }
    }


    fun loadPlayingMovies(){
        runBlocking(Dispatchers.IO) {
            val listIdPularMovies = categoryMoviesDao.getListIdNowPlaying()
            val moviesById = movieDao.getMoviesById(listIdPularMovies)
            lvdPlayingMovies.postValue(moviesById)
        }
    }


}
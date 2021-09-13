package com.gogaedd.salinasgroupmoviedb_gge.repository

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.gogaedd.salinasgroupmoviedb_gge.infraestructure.ConstantsApp
import com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.db.AppDatabase
import com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.net.NetListener
import com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.net.NetMovies
import com.gogaedd.salinasgroupmoviedb_gge.model.CategoryMovie
import com.gogaedd.salinasgroupmoviedb_gge.model.Movie
import com.gogaedd.salinasgroupmoviedb_gge.utils.UtilsNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashRepository(val application: Application) : NetListener {

    private val db = AppDatabase.getDatabase(application)
    private val movieDao = db.movieDao()
    private val categoryMoviesDao = db.categoryMovieDao()

    private val netMovies = NetMovies()

    private val lvdStateload = MutableLiveData<Int>(ConstantsApp.STATE_LOAD.START_LOADING)
    private val lvdMessageError = MutableLiveData<String>("")



    fun getLvdStateload()= lvdStateload
    fun getLvdMessageError()= lvdMessageError

    init {
        netMovies.setListener(this)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun startLoadCat() {
        runBlocking(Dispatchers.IO) {
            setStateLoad(ConstantsApp.STATE_LOAD.START_LOADING)
            if (UtilsNet.isOnline(application)) {
                deleteInfoDb()
                delay(500)
                getNowPlaying()
            } else {
                if (existContentDb()) {
                    //todo:pasar a sig pantalla
                    lvdStateload.postValue(ConstantsApp.STATE_LOAD.LOAD_OK)
                } else {
                    //todo: mensaje de que se requiere inter
                    setMessageError("Se requiere internet para descargar informacion")
                    setStateLoad(ConstantsApp.STATE_LOAD.LOAD_ERROR)
                }

            }
        }


    }

    private fun setStateLoad(state: Int) {
        lvdStateload.postValue( state)
    }

    fun setMessageError(message: String) {
        lvdMessageError.postValue( message)
    }

    private fun deleteInfoDb() {
        movieDao.deleteAllMovies()
        categoryMoviesDao.deleteTableCategoryMovie()

    }

    private fun existContentDb(): Boolean {

        val countTable = movieDao.getCountTable()
        val countTable1 = categoryMoviesDao.getCountTable()

        return countTable > 0 && countTable1 > 0
    }

    private fun getNowPlaying() {
        netMovies.sendRequestNowPlaying()
    }

    private fun getMostpopular() {
        netMovies.sendRequestMostPopular()
    }

    override fun onResultOk(any: Any, code: Int) {
        when (code) {
            ConstantsApp.NET.WS_NOW_PLAYING_GET -> {
                if (any is MutableList<*>) {
                    val listMoviesNowplaying = any as MutableList<Movie>

                    runBlocking(Dispatchers.IO) {
                        saveMoviesInDb(listMoviesNowplaying, true)
                        delay(1000)
                        getMostpopular()
                    }

                }
            }
            ConstantsApp.NET.WS_MOST_POPULAR_GET -> {
                if (any is MutableList<*>) {
                    val listMoviesPopular = any as MutableList<Movie>
                    runBlocking(Dispatchers.IO) {
                        saveMoviesInDb(listMoviesPopular, false)
                        //todo: Pasar a sig pantall
                        delay(7000)
                        lvdStateload.postValue(ConstantsApp.STATE_LOAD.LOAD_OK)

                    }
                }
            }
        }


    }


    override fun onResultError(meesageError: String, code: Int) {
        when (code) {
            ConstantsApp.NET.WS_NOW_PLAYING_GET -> {
                setMessageError("No se logro obtener playings movies")
            }
            ConstantsApp.NET.WS_MOST_POPULAR_GET -> {
                setMessageError("No se logro obtener popular movies")

            }

        }
        setStateLoad(ConstantsApp.STATE_LOAD.LOAD_ERROR)

    }



    private suspend fun saveMoviesInDb(
        listMovies: MutableList<Movie>,
        isNowPlaying: Boolean
    ) {
        //Table movie
        movieDao.insertAllMovies(listMovies)

        //Validacion si existe en table categoryMovie
        listMovies.forEach { movie ->
            categoryMoviesDao.getMovieWithCategory(movie.id)?.let { categoryStore ->

                //UPDATE
                if (isNowPlaying) {
                    categoryMoviesDao.updateStatusNowplaying(categoryStore.id)
                } else {
                    categoryMoviesDao.updateStatusPopular(categoryStore.id)
                }


            } ?: run {
                //INSERT
                val categoryMovie = if (isNowPlaying) {
                    CategoryMovie(
                        movie.id,
                        movie_now_playing = 1
                    )
                } else {
                    CategoryMovie(
                        movie.id,
                        movie_popular = 1
                    )
                }

                categoryMoviesDao.insertStatusMovie(categoryMovie)

            }
        }
    }


}
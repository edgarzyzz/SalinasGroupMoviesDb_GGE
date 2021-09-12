package com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gogaedd.salinasgroupmoviedb_gge.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: MutableList<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)


    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)


    @Query("delete from Movie")
    fun deleteAllMovies()

    @Query("select * from Movie")
    fun selectAll(): MutableList<Movie>

    @Query("select count(*) from Movie ")
    fun getCountTable():Int

    @Query("select * from Movie where id in (:listId)")
    fun getMoviesById(listId: List<String>) : MutableList<Movie>
}
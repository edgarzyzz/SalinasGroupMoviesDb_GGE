package com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.db

import androidx.room.*
import com.gogaedd.salinasgroupmoviedb_gge.model.CategoryMovie

@Dao
interface CategoryMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStatusMovie(catgoryMoview:CategoryMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllStatusMovie (vararg catgoryMoview:CategoryMovie)


    @Update
    fun updateStatusPopular(catgoryMoview: CategoryMovie)

    @Update
    fun updateStatusNowplaying(catgoryMoview: CategoryMovie)

    @Delete
    fun deleteSattusMoview(catgoryMovie: CategoryMovie)

    @Query("delete from CategoryMovie")
    fun deleteTableCategoryMovie()



    @Query("select id from CategoryMovie where movie_popular = 1")
    fun getListIdPularMovies(): List<String>

    @Query("select id from CategoryMovie where movie_now_playing = 1")
    fun getListIdNowPlaying(): List<String>


    @Query("select * from CategoryMovie where id = :id")
    fun getMovieWithCategory(id: String): CategoryMovie?

    @Query("Select count (*) from CategoryMovie")
    fun getCountTable(): Int

//    fun updateStatusNowPlaying()
}
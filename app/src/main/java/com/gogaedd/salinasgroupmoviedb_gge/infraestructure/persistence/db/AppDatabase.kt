package com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gogaedd.salinasgroupmoviedb_gge.model.CategoryMovie
import com.gogaedd.salinasgroupmoviedb_gge.model.Movie


@Database(entities = arrayOf(Movie::class, CategoryMovie::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun categoryMovieDao(): CategoryMovieDao

    companion object {
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"appDb.db")
                    .build()
            }
            return INSTANCE!!

        }
    }
}
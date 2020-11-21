package com.apusart.moviesliblary.api.local_data_source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class MoviesLibraryDatabase: RoomDatabase() {
    abstract fun userDao(): IUserDao


    companion object {
        private lateinit var applicationContext: Context

        val db by lazy {
            databaseBuilder(applicationContext, MoviesLibraryDatabase::class.java, "movies_library.db")
                .build()
        }

        fun initialize(appContext: Context) {
            applicationContext = appContext
        }
    }
}
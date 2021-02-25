package com.demo.bookmarklocation.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.bookmarklocation.model.LocationModel

@Database(entities = [LocationModel::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {

    abstract fun getMovieDao(): LocationDao

    companion object {

        private const val DB_NAME = "location.db"
        @Volatile private var instance: LocationDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LocationDatabase::class.java,
            DB_NAME
        ).build()
    }
}
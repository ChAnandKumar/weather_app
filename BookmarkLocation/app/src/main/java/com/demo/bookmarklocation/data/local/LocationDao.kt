package com.demo.bookmarklocation.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.bookmarklocation.model.LocationModel


@Dao
interface LocationDao {

    @Query("SELECT * FROM LocationModel")
    fun getAllLocation(): List<LocationModel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie : LocationModel)

//    @Query("SELECT * FROM MovieDetail WHERE imdbID = :imdbId")
//    fun getMovieDetail(imdbId: String): MovieDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(movieDetail : LocationModel)
}
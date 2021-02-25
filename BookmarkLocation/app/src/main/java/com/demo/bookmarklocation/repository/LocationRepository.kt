package com.demo.bookmarklocation.repository

import android.util.Log
import com.demo.bookmarklocation.data.local.LocationDao
import com.demo.bookmarklocation.model.LocationModel
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationDao: LocationDao) {

    suspend fun getLocationList(): List<LocationModel>? {
        /** As per requirement we will fetch data from the database first **/
        val moviesList = locationDao.getAllLocation()

        return moviesList;
    }

    suspend fun addLocation(locationModel: LocationModel) {
        /** As per requirement we will fetch data from the database first **/
         locationDao.insertLocation(locationModel)
    }

}
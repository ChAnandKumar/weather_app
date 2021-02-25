package com.demo.bookmarklocation.repository

import android.util.Log
import com.demo.bookmarklocation.data.remote.WeatherService
import com.demo.bookmarklocation.model.WeatherResponse
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService: WeatherService){


    suspend fun getWeather(latLng: LatLng): WeatherResponse {
        val weather = weatherService.getWeatherByGPS(latLng.longitude.toString(),latLng.latitude.toString())
        //val weather = weatherService.getWeatherByLocation(latLng.longitude.toString(),latLng.latitude.toString())

        Log.d("anand","$weather")
        return weather
    }
    suspend fun getWeatherWithCity(city: String): ResultWrapper<WeatherResponse>  {
        return safeApiCall(Dispatchers.IO){ weatherService.getWeatherByLocation(city)}
    }
}
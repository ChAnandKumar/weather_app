package com.demo.bookmarklocation.data.remote

import com.demo.bookmarklocation.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService{
    @GET("/data/2.5/weather")
    suspend fun getWeatherByLocation(
        @Query("q") location: String,
        @Query("units") units: String = "metric",//&units=metric
        @Query("appid") appid: String = "8bbfa5b7b7be3ef1bda226f866fad1fc"
    ): WeatherResponse

    @GET("/data/2.5/weather")
    suspend fun getWeatherByGPS(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = "8bbfa5b7b7be3ef1bda226f866fad1fc"
    ):WeatherResponse
}
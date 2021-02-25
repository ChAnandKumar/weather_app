package com.demo.bookmarklocation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cod")
    @Expose
    val cod: Int, // 200
    @SerializedName("dt")
    @Expose
    val dt: Long, // 1560350645
    @SerializedName("main")
    @Expose
    val main: Main,
    @SerializedName("name")
    @Expose
    val name: String, // Mountain View
    @SerializedName("sys")
    @Expose
    val sys: Sys,
    @SerializedName("timezone")
    @Expose
    val timezone: Int, // -25200
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>,
    @SerializedName("visibility")
    @Expose
    val visibility: Int?
) {
    data class Main(
        @SerializedName("humidity")
        @Expose
        val humidity: Double,
        @SerializedName("pressure")
        @Expose// 53
        val pressure: Double,
        @SerializedName("temp")
        @Expose// 1013
        val temp: Double,
        @SerializedName("temp_max")
        @Expose// 296.71
        val temp_max: Double,
        @SerializedName("temp_min")
        @Expose// 298.71
        val temp_min: Double // 294.82
    )

    data class Sys(
        @SerializedName("country")
        @Expose
        val country: String, // US
        @SerializedName("id")
        @Expose
        val id: Int, // 5122
        @SerializedName("message")
        @Expose
        val message: Double, // 0.0139
        @SerializedName("sunrise")
        @Expose
        val sunrise: Int, // 1560343627
        @SerializedName("sunset")
        @Expose
        val sunset: Int, // 1560396563
        @SerializedName("type")
        @Expose
        val type: Int // 1
    )

    data class Weather(
        val description: String, // clear sky
        val icon: String, // 01d
        val id: Int, // 800
        val main: String // Clear
    )
}



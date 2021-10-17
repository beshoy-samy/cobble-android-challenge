package com.cobble.weather.model.datasources.network

import com.cobble.weather.model.dtos.DailyWeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/forecast/daily")
    suspend fun getDailyWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") daysCount: Int,
        @Query("units") units: String
    ): DailyWeatherResponseModel
}
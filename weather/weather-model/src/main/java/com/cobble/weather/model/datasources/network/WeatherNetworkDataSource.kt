package com.cobble.weather.model.datasources.network

import com.cobble.weather.model.dtos.DailyWeatherResponseModel
import javax.inject.Inject

interface WeatherNetworkDataSource {

    suspend fun getDailyWeather(
        lat: Double,
        lon: Double,
        daysCount: Int,
        units: String
    ): DailyWeatherResponseModel
}

internal class WeatherNetworkDataSourceImp @Inject constructor(private val service: WeatherService) :
    WeatherNetworkDataSource {

    override suspend fun getDailyWeather(
        lat: Double,
        lon: Double,
        daysCount: Int,
        units: String
    ): DailyWeatherResponseModel = service.getDailyWeather(lat, lon, daysCount, units)

}
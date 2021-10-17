package com.cobble.weather.domain.contracts

import com.cobble.weather.domain.entities.DailyForecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeather(
        lat: Double,
        lon: Double,
        unit: String,
        daysCount: Int
    ): Flow<List<DailyForecast>>
}
package com.cobble.weather.domain.usecases

import com.cobble.weather.domain.contracts.WeatherRepository
import com.cobble.weather.domain.entities.DailyForecast
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetDailyWeatherUseCase {

    suspend fun getDailyWeather(
        lat: Double,
        lon: Double,
        unit: String = "imperial",
        daysCount: Int = 7
    ): Flow<List<DailyForecast>>
}

class GetDailyWeatherUseCaseImp @Inject constructor(private val weatherRepository: WeatherRepository) :
    GetDailyWeatherUseCase {

    override suspend fun getDailyWeather(
        lat: Double,
        lon: Double,
        unit: String,
        daysCount: Int
    ): Flow<List<DailyForecast>> = weatherRepository.getWeather(lat, lon, unit, daysCount)

}
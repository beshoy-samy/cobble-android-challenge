package com.cobble.weather.model.datasources.network

import com.cobble.weather.model.dtos.DailyWeatherResponseModel
import com.cobble.weather.model.dtos.ForecastResponseModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class WeatherNetworkDataSourceImpTest {

    @Test
    fun `getDailyWeather() with params then return DailyWeatherResponseModel`() = runBlocking {
        //arrange
        val mockedResponse = DailyWeatherResponseModel(
            forecast = listOf(
                ForecastResponseModel(date = 1L),
                ForecastResponseModel(date = 2L)
            )
        )

        val weatherService = object : WeatherService {

            override suspend fun getDailyWeather(
                lat: Double,
                lon: Double,
                daysCount: Int,
                units: String
            ): DailyWeatherResponseModel = mockedResponse
        }

        val weatherNetworkDataSource = WeatherNetworkDataSourceImp(weatherService)

        //act
        val result =
            weatherNetworkDataSource.getDailyWeather(0.0, 0.0, 2, "")

        assertTrue(mockedResponse == result)
    }

    @Test(expected = Exception::class)
    fun `getDailyWeather() then throw Exception`() = runBlocking {
        //arrange
        val weatherService = object : WeatherService {

            override suspend fun getDailyWeather(
                lat: Double,
                lon: Double,
                daysCount: Int,
                units: String
            ): DailyWeatherResponseModel = throw Exception("Network not found")
        }

        val weatherNetworkDataSource = WeatherNetworkDataSourceImp(weatherService)

        //act
        weatherNetworkDataSource.getDailyWeather(0.0, 0.0, 2, "")

        return@runBlocking
    }
}
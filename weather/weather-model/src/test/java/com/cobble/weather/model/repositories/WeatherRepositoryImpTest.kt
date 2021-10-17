package com.cobble.weather.model.repositories

import com.cobble.weather.domain.entities.DailyForecast
import com.cobble.weather.model.datasources.network.WeatherNetworkDataSource
import com.cobble.weather.model.dtos.DailyWeatherResponseModel
import com.cobble.weather.model.dtos.ForecastResponseModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class WeatherRepositoryImpTest {

    @Test
    fun `getWeather() with params then emit list of DailyForecast`() = runBlocking {
        //arrange
        val mockedResponse = DailyWeatherResponseModel(
            forecast = listOf(
                ForecastResponseModel(speed = 1F),
                ForecastResponseModel(speed = 2F)
            )
        )

        val expected =
            listOf(mockDailyForecast(windSpeed = 1), mockDailyForecast(windSpeed = 2))

        val weatherNetworkDataSource = object : WeatherNetworkDataSource {

            override suspend fun getDailyWeather(
                lat: Double,
                lon: Double,
                daysCount: Int,
                units: String
            ): DailyWeatherResponseModel = mockedResponse
        }

        val weatherRepository = WeatherRepositoryImp(weatherNetworkDataSource)

        //act
        val result = weatherRepository.getWeather(0.0, 0.0, "", 2).first()
        println(result)
        //assert
        assertTrue(result == expected)
    }

    @Test(expected = Exception::class)
    fun `getWeather() then throw Exception`() = runBlocking {
        //arrange
        val weatherNetworkDataSource = object : WeatherNetworkDataSource {

            override suspend fun getDailyWeather(
                lat: Double,
                lon: Double,
                daysCount: Int,
                units: String
            ): DailyWeatherResponseModel = throw Exception("Network Not Found")
        }

        val weatherRepository = WeatherRepositoryImp(weatherNetworkDataSource)

        //act
        weatherRepository.getWeather(0.0, 0.0, "", 2).collect()

        return@runBlocking
    }

    private fun mockDailyForecast(windSpeed: Int) =
        DailyForecast(0, false, "", "", "", "", 0, 0, "", windSpeed)

}
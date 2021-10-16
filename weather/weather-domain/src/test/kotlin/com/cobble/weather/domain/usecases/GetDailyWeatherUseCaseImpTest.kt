package com.cobble.weather.domain.usecases

import com.cobble.weather.domain.contracts.WeatherRepository
import com.cobble.weather.domain.entities.DailyForecast
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetDailyWeatherUseCaseImpTest {

    @Test
    fun `getDailyWeather() then emit listOf DailyForecast`() = runBlocking {
        //arrange
        val mockedDailyForecast = listOf(mockDailyForecast(11), mockDailyForecast(12))

        val weatherRepository = object : WeatherRepository {

            override suspend fun getWeather(
                lat: Double,
                lon: Double,
                unit: String,
                daysCount: Int
            ): Flow<List<DailyForecast>> = flowOf(mockedDailyForecast)
        }

        val getDailyWeatherUseCase = GetDailyWeatherUseCaseImp(weatherRepository)

        //act
        val result = getDailyWeatherUseCase.getDailyWeather(10.0, 11.0).first()

        //assert
        assertTrue(result == mockedDailyForecast)
    }

    @Test(expected = Exception::class)
    fun `getDailyWeather() then throw Exception`() = runBlocking {
        //arrange
        val weatherRepository = object : WeatherRepository {

            override suspend fun getWeather(
                lat: Double,
                lon: Double,
                unit: String,
                daysCount: Int
            ): Flow<List<DailyForecast>> = throw Exception("Network Not Found")
        }

        val getDailyWeatherUseCase = GetDailyWeatherUseCaseImp(weatherRepository)

        //act
        getDailyWeatherUseCase.getDailyWeather(10.0, 11.0).collect()

        return@runBlocking
    }

    private fun mockDailyForecast(date: Long) =
        DailyForecast(date, false, "", "", "", "", 0, 0, "", 0)
}
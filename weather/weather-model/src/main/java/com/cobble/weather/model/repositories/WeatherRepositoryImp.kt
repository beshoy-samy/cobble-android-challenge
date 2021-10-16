package com.cobble.weather.model.repositories

import com.cobble.weather.domain.contracts.WeatherRepository
import com.cobble.weather.domain.entities.DailyForecast
import com.cobble.weather.model.datasources.network.WeatherNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

internal class WeatherRepositoryImp @Inject constructor(private val dataSource: WeatherNetworkDataSource) :
    WeatherRepository {

    override suspend fun getWeather(
        lat: Double,
        lon: Double,
        unit: String,
        daysCount: Int
    ): Flow<List<DailyForecast>> = flow {
        val response = dataSource.getDailyWeather(lat, lon, daysCount, unit)
        emit(response)
    }.map { response ->
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val isNight = now.hour < 6 || now.hour > 18
        response.forecast?.map { forecast ->
            val date = forecast.date
            val dateTime = date?.let { localeDateTime(date) }
            DailyForecast(
                date = date ?: 0,
                isToday = dateTime?.dayOfYear == now.dayOfYear,
                dayOfWeek = dateTime?.dayOfWeek?.name.orEmpty(),
                monthTime = date.format(format = "MMMM, dd").orEmpty(),
                sunriseTime = forecast.sunrise.format().orEmpty(),
                sunsetTime = forecast.sunset.format().orEmpty(),
                temp = if (isNight) forecast.temp?.night?.roundToInt() ?: 0
                else forecast.temp?.day?.roundToInt() ?: 0,
                weatherIcon = forecast.weather?.firstOrNull()?.id ?: 0,
                weatherDesc = forecast.weather?.firstOrNull()?.description.orEmpty(),
                windSpeed = forecast.speed?.roundToInt() ?: 0
            )
        }.orEmpty()
    }

    private fun localeDateTime(millis: Long) =
        Instant.fromEpochSeconds(millis).toLocalDateTime(TimeZone.currentSystemDefault())

    private fun Long?.format(format: String = "hh:mm a"): String? = this?.let { millis ->
        val time = Instant.fromEpochSeconds(millis).toString()
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = dateFormatter.parse(time)
        date?.let { SimpleDateFormat(format, Locale.getDefault()).format(date) }
    }
}
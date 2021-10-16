package com.cobble.weather.model.dtos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponseModel(
    @Json(name = "clouds")
    val clouds: Int? = null,
    @Json(name = "deg")
    val deg: Int? = null,
    @Json(name = "dt")
    val date: Long? = null,
    @Json(name = "feels_like")
    val feelsLike: FeelsLikeResponseModel? = null,
    @Json(name = "gust")
    val gust: Float? = null,
    @Json(name = "humidity")
    val humidity: Int? = null,
    @Json(name = "pop")
    val pop: Float? = null,
    @Json(name = "pressure")
    val pressure: Float? = null,
    @Json(name = "speed")
    val speed: Float? = null,
    @Json(name = "sunrise")
    val sunrise: Long? = null,
    @Json(name = "sunset")
    val sunset: Long? = null,
    @Json(name = "temp")
    val temp: TempResponseModel? = null,
    @Json(name = "weather")
    val weather: List<WeatherResponseModel>? = null
)
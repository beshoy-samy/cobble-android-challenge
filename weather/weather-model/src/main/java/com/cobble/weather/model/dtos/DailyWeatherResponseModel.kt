package com.cobble.weather.model.dtos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyWeatherResponseModel(
    @Json(name = "city")
    val city: CityResponseModel? = null,
    @Json(name = "cnt")
    val count: Int? = null,
    @Json(name = "cod")
    val cod: String? = null,
    @Json(name = "list")
    val forecast: List<ForecastResponseModel>? = null,
    @Json(name = "message")
    val message: Double? = null
)
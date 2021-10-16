package com.cobble.weather.model.dtos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityResponseModel(
    @Json(name = "coord")
    val coord: CoordResponseModel? = null,
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "population")
    val population: Int? = null,
    @Json(name = "timezone")
    val timezone: Int? = null
)
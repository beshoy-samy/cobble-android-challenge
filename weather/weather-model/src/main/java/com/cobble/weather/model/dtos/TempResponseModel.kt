package com.cobble.weather.model.dtos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TempResponseModel(
    @Json(name = "day")
    val day: Double? = null,
    @Json(name = "eve")
    val eve: Double? = null,
    @Json(name = "max")
    val max: Double? = null,
    @Json(name = "min")
    val min: Double? = null,
    @Json(name = "morn")
    val morn: Double? = null,
    @Json(name = "night")
    val night: Double? = null
)
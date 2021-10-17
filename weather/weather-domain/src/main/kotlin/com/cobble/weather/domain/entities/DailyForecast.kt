package com.cobble.weather.domain.entities


data class DailyForecast(
    val date: Long,
    val isToday: Boolean,
    val dayOfWeek: String,
    val monthTime: String,
    val sunriseTime: String,
    val sunsetTime: String,
    val temp: Int,
    val weatherIcon: Int,
    val weatherDesc: String,
    val windSpeed: Int,
    val unit: String = "°F",
    val isExpanded: Boolean = false
)
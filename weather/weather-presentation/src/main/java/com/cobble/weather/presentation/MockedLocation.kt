package com.cobble.weather.presentation

data class Location(
    val lat: Double,
    val lon: Double,
    val name: String
)

fun mockLocations() = listOf(
    Location(lat = 40.6976701, lon = -74.2598705, name = "New York"),
    Location(lat = 51.5287352, lon = -0.3817815, name = "London"),
    Location(lat = 35.6684415, lon = 139.6007818, name = "Tokyo"),
    Location(lat = 48.8589466, lon = 2.276995, name = "Paris"),
    Location(lat = 30.0596185, lon = 31.1884238, name = "Texarkana")
)

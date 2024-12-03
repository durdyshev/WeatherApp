package com.justme.weatherapp.data.remote

import com.squareup.moshi.Json

class WeatherDto (
    @field:Json(name = "hourly")
    val weatherData:WeatherDataDto
)
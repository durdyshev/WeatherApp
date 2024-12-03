package com.justme.weatherapp.presentation

import com.justme.weatherapp.domain.weather.WeatherInfo


data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val locationName:String?= "New York"
)

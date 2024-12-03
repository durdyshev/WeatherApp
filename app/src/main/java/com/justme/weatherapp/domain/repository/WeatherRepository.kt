package com.justme.weatherapp.domain.repository

import com.justme.weatherapp.domain.util.Resource
import com.justme.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}
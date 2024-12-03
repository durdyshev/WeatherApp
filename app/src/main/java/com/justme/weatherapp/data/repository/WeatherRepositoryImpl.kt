package com.justme.weatherapp.data.repository

import com.justme.weatherapp.data.mappers.toWeatherInfo
import com.justme.weatherapp.data.remote.WeatherApi
import com.justme.weatherapp.domain.repository.WeatherRepository
import com.justme.weatherapp.domain.util.Resource
import com.justme.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(data = api.getWeatherData(lat, long).toWeatherInfo())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}
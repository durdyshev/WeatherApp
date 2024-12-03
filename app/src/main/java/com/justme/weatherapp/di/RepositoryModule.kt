package com.justme.weatherapp.di

import com.justme.weatherapp.data.location.DefaultLocationTracker
import com.justme.weatherapp.data.repository.WeatherRepositoryImpl
import com.justme.weatherapp.domain.location.LocationTracker
import com.justme.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}
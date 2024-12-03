package com.justme.weatherapp.domain.weather

import androidx.annotation.DrawableRes

data class AirQualityItem(
    @DrawableRes val icon: Int,
    val title: String,
    val value: String
)
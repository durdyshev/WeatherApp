package com.justme.weatherapp.domain.weather

import androidx.annotation.DrawableRes

data class ForecastItem(
    @DrawableRes val image: Int,
    val dayOfWeek: String,
    val date: String,
    val temperature: String,
    val airQuality: String,
    val airQualityIndicatorColorHex: String,
    val isSelected: Boolean = false
)
package com.justme.weatherapp.presentation.screen

import android.annotation.SuppressLint
import com.justme.weatherapp.presentation.screen.components.ActionBar
import com.justme.weatherapp.presentation.screen.components.AirQuality
import com.justme.weatherapp.presentation.screen.components.DailyForecast
import com.justme.weatherapp.presentation.screen.components.WeeklyForecast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.justme.weatherapp.presentation.WeatherState

@SuppressLint("NewApi")
@Composable
fun WeatherScreen(
    state: WeatherState? = null,
    paddings: PaddingValues,
    onRefresh: () -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state?.isLoading ?: false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddings)
                .padding(horizontal = 24.dp, vertical = 10.dp)
        ) {
            ActionBar(state = state, refreshing = swipeRefreshState.isRefreshing)
            Spacer(modifier = Modifier.height(12.dp))
            DailyForecast(state = state)
            Spacer(modifier = Modifier.height(24.dp))
            AirQuality(state = state)
            Spacer(modifier = Modifier.height(24.dp))
            WeeklyForecast(state = state)
        }
    }
}

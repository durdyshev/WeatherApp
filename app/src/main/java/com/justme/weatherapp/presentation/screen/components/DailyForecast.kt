package com.justme.weatherapp.presentation.screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.justme.weatherapp.R
import com.justme.weatherapp.presentation.WeatherState
import com.justme.weatherapp.ui.theme.ColorGradient1
import com.justme.weatherapp.ui.theme.ColorGradient2
import com.justme.weatherapp.ui.theme.ColorGradient3
import com.justme.weatherapp.ui.theme.ColorTextSecondary
import com.justme.weatherapp.ui.theme.ColorTextSecondaryVariant
import com.justme.weatherapp.ui.theme.ColorWindForecast
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecast(
    state: WeatherState? = null,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (forecastImage, forecastValue, title, description, background) = createRefs()

        CardBackground(
            modifier = Modifier.constrainAs(background) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = parent.top,
                    bottom = description.bottom,
                    topMargin = 24.dp
                )
                height = Dimension.fillToConstraints
            }
        )

        state?.weatherInfo?.currentWeatherData?.weatherType?.iconRes?.let { painterResource(id = it) }
            ?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier
                        .height(130.dp)
                        .width(100.dp)
                        .constrainAs(forecastImage) {
                            start.linkTo(anchor = parent.start, margin = 24.dp)
                            top.linkTo(parent.top)
                        }
                )
            }

        Text(
            text = state?.weatherInfo?.currentWeatherData?.weatherType?.weatherDesc ?: "",
            style = MaterialTheme.typography.titleLarge,
            color = ColorTextSecondary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(anchor = parent.start, margin = 24.dp)
                top.linkTo(anchor = forecastImage.bottom)
            }
        )

        Text(
            text = state?.weatherInfo?.currentWeatherData?.time?.dayOfWeek?.name.toString()
                .lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } + ", "
                    + state?.weatherInfo?.currentWeatherData?.time?.dayOfMonth + " " + state?.weatherInfo?.currentWeatherData?.time?.month.toString()
                .lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            style = MaterialTheme.typography.bodyMedium,
            color = ColorTextSecondaryVariant,
            modifier = Modifier
                .constrainAs(description) {
                    start.linkTo(anchor = title.start)
                    top.linkTo(anchor = title.bottom)
                }
                .padding(bottom = 24.dp)
        )

        ForecastValue(
            state = state,
            modifier = Modifier.constrainAs(forecastValue) {
                end.linkTo(anchor = parent.end, margin = 24.dp)
                top.linkTo(forecastImage.top)
                bottom.linkTo(forecastImage.bottom)
            }
        )

      /*  WindForecastImage(
            modifier = Modifier.constrainAs(windImage) {
                linkTo(
                    top = title.top,
                    bottom = title.bottom
                )
                end.linkTo(anchor = parent.end, margin = 24.dp)
            }
        )*/
    }
}

@Composable
private fun CardBackground(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    0f to ColorGradient1,
                    0.5f to ColorGradient2,
                    1f to ColorGradient3
                ),
                shape = RoundedCornerShape(32.dp)
            )
    )
}

@Composable
private fun ForecastValue(
    state: WeatherState? = null,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.TopEnd,
        ) {
            Text(
                text = state?.weatherInfo?.currentWeatherData?.temperatureCelsius?.toInt()?.toString() ?: "",
                letterSpacing = 0.sp,
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        0f to Color.White,
                        1f to Color.White.copy(alpha = 0.3f)
                    ),
                    fontSize = 80.sp,
                    fontWeight = FontWeight.Black
                ),
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = "°",
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        0f to Color.White,
                        1f to Color.White.copy(alpha = 0.3f)
                    ),
                    fontSize = 70.sp,
                    fontWeight = FontWeight.Light,
                ),
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        Text(
            text = "Feels like ${state?.weatherInfo?.currentWeatherData?.apparentTemperature?.toInt()?:""}°",
            style = MaterialTheme.typography.bodyMedium,
            color = ColorTextSecondaryVariant
        )
    }
}

@Composable
private fun WindForecastImage(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_frosty),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = ColorWindForecast
        )
        Icon(
            painter = painterResource(R.drawable.ic_wind),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = ColorWindForecast
        )
    }
}



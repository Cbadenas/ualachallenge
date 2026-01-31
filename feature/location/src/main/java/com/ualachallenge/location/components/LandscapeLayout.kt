package com.ualachallenge.location.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.ualachallenge.domain.city.City
import com.ualachallenge.location.CityScreenEvent
import com.ualachallenge.location.CityScreenUiState
import com.ualachallenge.location.map.MapScreen

@Composable
fun LandscapeLayout(
    modifier: Modifier,
    uiState: CityScreenUiState,
    selectedCity: City?,
    onEvent: (CityScreenEvent) -> Unit,
    onCitySelected: (City) -> Unit
) {
    Row(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(0.33f)) {
            SearchAndList(
                uiState = uiState,
                onEvent = onEvent,
                onCitySelected = onCitySelected
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            if (selectedCity != null) {
                MapScreen(
                    lat = selectedCity.coord.lat,
                    lon = selectedCity.coord.lon,
                    cityName = selectedCity.name,
                    onUpClick = {},
                    isLandscape = true,
                )
            } else {
                Text("Selecciona una ciudad para ver el mapa", textAlign = TextAlign.Center)
            }
        }
    }
}
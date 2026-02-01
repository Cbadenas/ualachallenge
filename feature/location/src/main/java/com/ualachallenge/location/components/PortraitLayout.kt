package com.ualachallenge.location.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ualachallenge.domain.city.City
import com.ualachallenge.location.CityScreenEvent
import com.ualachallenge.location.CityScreenUiState

@Composable
fun PortraitLayout(
    modifier: Modifier,
    uiState: CityScreenUiState,
    onEvent: (CityScreenEvent) -> Unit,
    onCitySelected: (City) -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        SearchAndList(
            uiState = uiState,
            onEvent = onEvent,
            onCitySelected = onCitySelected,
            onFavoriteClick = onFavoriteClick
        )
    }
}
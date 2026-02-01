package com.ualachallenge.location

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ualachallenge.domain.city.City
import com.ualachallenge.location.components.LandscapeLayout
import com.ualachallenge.location.components.PortraitLayout

@Composable
fun SearchCitiesScreen(
    viewModel: CitiesViewModel = hiltViewModel(),
    modifier: Modifier,
    onCitySelectedForMap: (City) -> Unit,
    onNavigateToDetails: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedCity by rememberSaveable { mutableStateOf<City?>(viewModel.uiState.value.selectedCity) }

    LaunchedEffect(Unit) {
        if (uiState.cities.isEmpty()) {
            viewModel.onEvent(CityScreenEvent.OnGetAllCities)
        }
    }

    val isLandscape =
        LocalConfiguration.current.screenWidthDp > LocalConfiguration.current.screenHeightDp

    if (isLandscape) {
        LandscapeLayout(
            modifier,
            uiState = uiState,
            selectedCity = selectedCity,
            onEvent = viewModel::onEvent,
            onCitySelected = { city -> selectedCity = city },
            onFavoriteClick = {
                viewModel.onEvent(CityScreenEvent.OnFavoriteClick(it))
            },
            onNavigateToDetails = {
                onNavigateToDetails(selectedCity!!.id)
            },
        )
    } else {
        PortraitLayout(
            modifier,
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onCitySelected = { city ->
                selectedCity = city
                onCitySelectedForMap.invoke(city)
            },
            onFavoriteClick = {
                viewModel.onEvent(CityScreenEvent.OnFavoriteClick(it))
            }
        )
    }
}
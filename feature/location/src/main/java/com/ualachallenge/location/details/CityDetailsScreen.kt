package com.ualachallenge.location.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ualachallenge.location.CitiesViewModel
import com.ualachallenge.location.CityScreenEvent
import com.ualachallenge.location.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailScreen(
    viewModel: CitiesViewModel = hiltViewModel(),
    cityId: Int,
    onUpClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val city = uiState.cities.find { it.id.toInt() == cityId }

    LaunchedEffect(key1 = Unit) {
        if (uiState.cities.isEmpty()) {
            viewModel.onEvent(CityScreenEvent.OnGetAllCities)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(city?.name ?: stringResource(R.string.city_detail_title)) },
                navigationIcon = {
                    IconButton(onClick = onUpClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (city != null) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row {
                            Text(
                                text = city.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(onClick = { viewModel.onEvent(CityScreenEvent.OnFavoriteClick(city.id.toInt())) }) {
                                Icon(
                                    imageVector = if (city.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Favorito"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = stringResource(R.string.city_detail_country, city.country), fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = stringResource(R.string.city_detail_id, city.id), fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = stringResource(R.string.city_detail_coordinates), fontWeight = FontWeight.SemiBold)
                        Text(text = stringResource(R.string.city_detail_latitude, city.coord.lat))
                        Text(text = stringResource(R.string.city_detail_longitude, city.coord.lon))
                    }
                }
            } else {
                Text(text = stringResource(R.string.city_detail_loading))
            }
        }
    }
}

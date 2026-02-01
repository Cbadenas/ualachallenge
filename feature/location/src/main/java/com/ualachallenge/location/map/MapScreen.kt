package com.ualachallenge.location.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ualachallenge.location.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    lat: Double,
    lon: Double,
    cityName: String?,
    onUpClick: () -> Unit,
    onNavigateToDetails: () -> Unit,
    isLandscape: Boolean = false
) {
    val cityPosition = LatLng(lat.toDouble(), lon.toDouble())

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cityPosition, 12f)
    }

    LaunchedEffect(lat, lon) {
        cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(cityPosition, 14f),
            1000
        )
    }

    Scaffold(
        topBar = {
            if (!isLandscape){
                TopAppBar(
                    title = { Text(text = cityName ?: stringResource(R.string.map_screen_title)) },
                    navigationIcon = {
                        IconButton(onClick = onUpClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver atrás"
                            )
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = cityPosition),
                    title = cityName ?: stringResource(R.string.map_selected_location),
                    snippet = "Lat: $lat, Lon: $lon"
                )
            }

            Button(
                onClick = onNavigateToDetails,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(text = stringResource(R.string.map_button_see_details))
            }
        }
    }
}

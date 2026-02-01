package com.ualachallenge.location.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ualachallenge.common.UIStatus
import com.ualachallenge.domain.city.City
import com.ualachallenge.location.CityScreenEvent
import com.ualachallenge.location.CityScreenUiState
import com.ualachallenge.location.R

@Composable
fun SearchAndList(
    uiState: CityScreenUiState,
    onEvent: (CityScreenEvent) -> Unit,
    onCitySelected: (City) -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    Column {
        OutlinedTextField(
            value = uiState.criteria,
            onValueChange = { onEvent(CityScreenEvent.CriteriaChanged(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text(stringResource(R.string.search_textfield_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true
        )

        when (val status = uiState.status) {
            UIStatus.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UIStatus.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = status.msg, color = MaterialTheme.colorScheme.error)
                }
            }

            UIStatus.Success, UIStatus.Idle -> {
                CitiesList(
                    cities = uiState.cities,
                    onCityClick = onCitySelected,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}
package com.ualachallenge.location.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ualachallenge.domain.city.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesList(
    cities: List<City>,
    onCityClick: (City) -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(cities, key = { it.id }) { city ->
            ListItem(
                headlineContent = { Text(city.name) },
                supportingContent = { Text(city.country) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCityClick(city) },
                trailingContent = {
                    IconButton(onClick = { onFavoriteClick(city.id) }) {
                        if (city.isFavorite) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Quitar de favoritos"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = "Marcar como favorito"
                            )
                        }
                    }
                }
            )
        }
    }
}
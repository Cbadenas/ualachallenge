package com.ualachallenge.location

import com.ualachallenge.common.UIStatus
import com.ualachallenge.domain.city.City

data class CityScreenUiState(
    val status: UIStatus = UIStatus.Idle,
    val isLoading: Boolean = false,

    val cities: List<City> = emptyList(),
    val criteria: String = "",
    val filteredCities: List<City> = emptyList(),

    val selectedCity: City? = null,

    val error: String? = null
)
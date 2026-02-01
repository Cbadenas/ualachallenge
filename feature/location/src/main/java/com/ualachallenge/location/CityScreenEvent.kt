package com.ualachallenge.location

sealed interface CityScreenEvent {

    object OnGetAllCities : CityScreenEvent
    data class CriteriaChanged(val criteria: String) : CityScreenEvent
    data class OnFavoriteClick(val cityId: Int) : CityScreenEvent

}
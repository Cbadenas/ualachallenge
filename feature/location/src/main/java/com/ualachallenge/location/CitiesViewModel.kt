package com.ualachallenge.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ualachallenge.common.UIStatus
import com.ualachallenge.domain.city.GetAllCitiesUseCase
import com.ualachallenge.domain.city.GetCitiesByCriteriaUsecase
import com.ualachallenge.domain.city.RemoveFavoritedCityUsecase
import com.ualachallenge.domain.city.SaveFavoritedCityUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val getCityByCreiteriaUseCase: GetCitiesByCriteriaUsecase,
    private val saveFavoriteCityUseCase: SaveFavoritedCityUsecase,
    private val removeFavoriteCityUseCase: RemoveFavoritedCityUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CityScreenUiState())
    val uiState: StateFlow<CityScreenUiState> = _uiState.asStateFlow()

    fun onEvent(event: CityScreenEvent) {
        when (event) {
            CityScreenEvent.OnGetAllCities -> getAllCities()
            is CityScreenEvent.CriteriaChanged -> getCitiesByCriteria(event.criteria)
            is CityScreenEvent.OnFavoriteClick -> toggleFavorite(event.cityId)

        }
    }

    fun toggleFavorite(cityId: Int) = viewModelScope.launch {
        val city = _uiState.value.cities.find { it.id.toInt() == cityId } ?: return@launch

        val result = if (city.isFavorite) {
            removeFavoriteCityUseCase(cityId)
        } else {
            saveFavoriteCityUseCase(cityId)
        }

        if (result.isSuccess) {
            val updatedCities = _uiState.value.cities.map {
                if (it.id.toInt() == cityId) {
                    it.copy(isFavorite = !it.isFavorite)
                } else {
                    it
                }
            }
            _uiState.update { it.copy(cities = updatedCities) }
        }
    }

    fun getAllCities() = viewModelScope.launch {
        _uiState.update { it.copy(status = UIStatus.Loading) }
        getAllCitiesUseCase()
            .onSuccess { cities ->
                _uiState.update {
                    it.copy(
                        status = UIStatus.Idle,
                        cities = cities
                    )
                }
            }
            .onFailure { e ->
                _uiState.update {
                    it.copy(
                        status = UIStatus.Error(e.message ?: "Ocurrió un error desconocido")
                    )
                }
            }
    }

    fun getCitiesByCriteria(criteria: String) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                status = UIStatus.Loading,
                criteria = criteria
            )
        }
        getCityByCreiteriaUseCase(criteria)
            .onSuccess { cities ->
                _uiState.update {
                    it.copy(
                        status = UIStatus.Idle,
                        cities = cities
                    )
                }
            }
            .onFailure { e ->
                _uiState.update {
                    it.copy(
                        status = UIStatus.Error(e.message ?: "Ocurrió un error desconocido")
                    )
                }
            }
    }

}
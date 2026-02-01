package com.ualachallenge.ports

import com.ualachallenge.domain.city.City

interface CityRepositoryPort {
    suspend fun getCities(): Result<List<City>>
    suspend fun getCityByCreiteria(criteria: String): Result<List<City>>
    suspend fun saveFavoriteCity(cityId: Int): Result<Unit>
    suspend fun removeFavoriteCity(cityId: Int): Result<Unit>
    suspend fun getFavoriteCities(): Result<List<Int>>
}
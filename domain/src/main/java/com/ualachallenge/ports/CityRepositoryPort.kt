package com.ualachallenge.ports

import com.ualachallenge.domain.City

interface CityRepositoryPort {
    suspend fun getCities(): Result<List<City>>
    suspend fun getCityByCreiteria(criteria: String): Result<List<City>>
}
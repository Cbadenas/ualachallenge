package com.ualachallenge.network.adapters

import com.ualachallenge.domain.City
import com.ualachallenge.network.CitiesDataSource
import com.ualachallenge.network.mapper.toDomain
import com.ualachallenge.ports.CityRepositoryPort
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryAdapter @Inject constructor(
    private val dataSource: CitiesDataSource
) : CityRepositoryPort {

    private var cachedCities: List<City>? = null

    override suspend fun getCities(): Result<List<City>> {

        cachedCities?.let {
            return Result.success(it)
        }
        return try {
            val cities = dataSource.getCities().map { it.toDomain() }
            cachedCities = cities

            return Result.success(cities)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getCityByCreiteria(criteria: String): Result<List<City>> {

        val filteredCities = dataSource.getCities().map { it.toDomain() }

        return Result.success(filteredCities)
    }

}
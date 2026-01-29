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


//    ● We define a prefix string as: a substring that matches the initial characters of the
//    target string. For instance, assume the following entries:
//    ○ Alabama, US
//    ○ Albuquerque, US
//    ○ Anaheim, US
//    ○ Arizona, US
//    ○ Sydney, AU
//
//    www.uala.com.ar
//
//    ● If the given prefix is "A", all cities but Sydney should appear. Contrariwise, if the given
//    prefix is "s", the only result should be "Sydney, AU".
//    ● If the given prefix is "Al", "Alabama, US" and "Albuquerque, US" are the only results.
//    ● If the prefix given is "Alb" then the only result is "Albuquerque, US"
    override suspend fun getCityByCreiteria(criteria: String): Result<List<City>> {
        return try {
            val cities = dataSource.getCities().map { it.toDomain() }

            val filteredCities = cities.filter { it.name.startsWith(criteria) }

            return Result.success(filteredCities)
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }

}
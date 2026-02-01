package com.ualachallenge.network.adapters

import com.ualachallenge.common.db.FavoriteCityDao
import com.ualachallenge.common.db.FavoriteCityEntity
import com.ualachallenge.domain.city.City
import com.ualachallenge.network.CitiesDataSource
import com.ualachallenge.network.mapper.toDomain
import com.ualachallenge.ports.CityRepositoryPort
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryAdapter @Inject constructor(
    private val dataSource: CitiesDataSource,
    private val favoriteCityDao: FavoriteCityDao
) : CityRepositoryPort {

    private var cachedCities: List<City>? = null

    override suspend fun getCities(): Result<List<City>> {
        val favoriteIds = favoriteCityDao.getAllFavoriteIds().first().toSet()

        cachedCities?.let { cities ->
            val updatedCities = cities.map { it.copy(isFavorite = favoriteIds.contains(it.id.toInt())) }
            return Result.success(updatedCities)
        }
        return try {
            val cities = dataSource.getCities().map {
                it.toDomain(
                    isFavorite = favoriteIds.contains(it.id)
                )
            }
            cachedCities = cities
            Result.success(cities)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getCityByCreiteria(criteria: String): Result<List<City>> {
        return try {
            val citiesResult = getCities()
            if (citiesResult.isSuccess) {
                val allCities = citiesResult.getOrThrow()
                val filteredCities = allCities.filter { it.name.contains(criteria, ignoreCase = false) }
                Result.success(filteredCities)
            } else {
                citiesResult // Propagamos el error si lo hubo
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }

    override suspend fun saveFavoriteCity(cityId: Int): Result<Unit> {
        return try {
            val entity = FavoriteCityEntity(cityId = cityId)
            favoriteCityDao.addFavorite(entity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun removeFavoriteCity(cityId: Int): Result<Unit>{
        return try {
            val entity = FavoriteCityEntity(cityId = cityId)
            favoriteCityDao.removeFavorite(entity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFavoriteCities(): Result<List<Int>> {
        return try {
            val favoriteIds = favoriteCityDao.getAllFavoriteIds().first()
            Result.success(favoriteIds)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
package com.ualachallenge.network.adapters

import android.app.Application
import com.ualachallenge.domain.City
import com.ualachallenge.network.dto.CityDto
import com.ualachallenge.network.mapper.toDomain
import com.ualachallenge.ports.CityRepositoryPort
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryAdapter @Inject constructor(
    private val application: Application,
    private val json: Json
) : CityRepositoryPort {

    private var cachedCities: List<City>? = null

    override suspend fun getCities(): Result<List<City>> {

        cachedCities?.let {
            return Result.success(it)
        }
        return try {

            val jsonString = application.assets.open("cities.json")
                .bufferedReader()
                .use { it.readText() }

            val citiesDto = json.decodeFromString<List<CityDto>>(jsonString)

            val cityList = citiesDto.map { it.toDomain() }

            cachedCities = cityList
            Result.success(cityList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
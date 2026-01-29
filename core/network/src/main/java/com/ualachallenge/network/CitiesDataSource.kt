package com.ualachallenge.network

import android.content.Context
import com.ualachallenge.network.dto.CityDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CitiesDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val json: Json
) {

    private var cachedCities: List<CityDto>? = null

    fun getCities(): List<CityDto> {
        cachedCities?.let {
            return it
        }
        try {

            val jsonString = context.assets.open("cities.json")
                .bufferedReader()
                .use { it.readText() }

            return json.decodeFromString<List<CityDto>>(jsonString)

        } catch (e: Exception) {
            //TODO: We should catch errors
            return emptyList()
        }
    }

}
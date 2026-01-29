package com.ualachallenge.network

import android.app.Application
import android.content.res.AssetManager
import com.ualachallenge.network.CitiesDataSource
import com.ualachallenge.network.adapters.CityRepositoryAdapter
import com.ualachallenge.network.dto.CityDto
import com.ualachallenge.network.dto.CoordDto
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CitiesDataSourceTest {

    val mockApplication = mockk<Application>()
    val mockAssetManager = mockk<AssetManager>()
    private lateinit var json: Json
    private lateinit var mockCitiesDataSource: CitiesDataSource

    @Before
    fun setUp() {

        json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        val fakeJson = """
        [
          {"country":"NL","name":"Gemeente Wieringen","_id":2744540,"coord":{"lon":4.95,"lat":52.900002}},
          {"country":"NL","name":"Smerp","_id":2747083,"coord":{"lon":4.99167,"lat":52.924999}},
          {"country":"ES","name":"Tremp","_id":6358961,"coord":{"lon":0.89212,"lat":42.165249}},
          {"country":"ES","name":"la Pobla de Segur","_id":3113298,"coord":{"lon":0.96667,"lat":42.25}},
          {"country":"ES","name":"Ciudad Jardín","_id":2519403,"coord":{"lon":-0.9859,"lat":37.611389}}
        ]
    """.trimIndent()

        val inputStream = fakeJson.byteInputStream()

        every { mockApplication.assets } returns mockAssetManager
        every { mockAssetManager.open("cities.json") } returns inputStream

        mockCitiesDataSource = CitiesDataSource(mockApplication, json)
    }

    //CHECK THAT THE DATASOURCE RETURNS SOMETHING
    @Test
    fun `DataSource getCities should return a list of cities`() = runTest {
        val cities = mockCitiesDataSource.getCities()
        assertNotNull(cities)
    }

    //CHECK THAT THE DATASOURCE RETURNS VALUES
    @Test
    fun `DataSource getCities should return a not empty list of cities`() = runTest {
        val cities = mockCitiesDataSource.getCities()
        assert(cities.isNotEmpty())
    }

}
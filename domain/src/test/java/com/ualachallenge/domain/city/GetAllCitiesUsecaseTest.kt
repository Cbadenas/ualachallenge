package com.ualachallenge.domain.city

import com.ualachallenge.ports.CityRepositoryPort
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetAllCitiesUsecaseTest {

    private lateinit var cityRepository: CityRepositoryPort
    private lateinit var getAllCitiesUseCase: GetAllCitiesUseCase

    @Before
    fun setUp() {
        cityRepository = mockk()
        getAllCitiesUseCase = GetAllCitiesUseCase(cityRepository)
    }

    @Test
    fun `should return a list of cities when repository returns success`() = runTest {
        val mockCities = listOf(
            City(
                id = 1,
                name = "Buenos Aires",
                country = "AR",
                coord = Coord(lon = -58.38, lat = -34.61)
            ),
            City(
                id = 2,
                name = "Córdoba",
                country = "AR",
                coord = Coord(lon = -64.18, lat = -31.41)
            )
        )
        coEvery { cityRepository.getCities() } returns Result.success(mockCities)

        val result = getAllCitiesUseCase()

        coVerify(exactly = 1) { cityRepository.getCities() }

        assertTrue(result.isSuccess)
        assertEquals(mockCities, result.getOrNull())
        assertEquals(2, result.getOrNull()?.size)
    }

    @Test
    fun `should return a Result failure when repository fails`() = runTest {
        val errorMessage = "Network Error"
        val exception = RuntimeException(errorMessage)
        coEvery { cityRepository.getCities() } returns Result.failure(exception)

        val result = getAllCitiesUseCase()

        coVerify(exactly = 1) { cityRepository.getCities() }

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

}
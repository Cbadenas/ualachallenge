package com.ualachallenge.domain.city

import com.ualachallenge.ports.CityRepositoryPort
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class GetCitiesByCriteriaUsecaseTest {

    private lateinit var cityRepository: CityRepositoryPort
    private lateinit var getCitiesByCriteriaUseCase: GetCitiesByCriteriaUsecase

    @Before
    fun setUp() {
        cityRepository = mockk()
        getCitiesByCriteriaUseCase = GetCitiesByCriteriaUsecase(cityRepository)
    }

    @Test
    fun `should return a filtered list of cities when repository returns success`() = runTest {
        val criteria = "Bueno"
        val mockFilteredCities = listOf(
            City(
                id = 1,
                name = "Buenos Aires",
                country = "AR",
                coord = Coord(lon = -58.38, lat = -34.61)
            )
        )
        coEvery { cityRepository.getCityByCreiteria(criteria) } returns Result.success(mockFilteredCities)

        val result = getCitiesByCriteriaUseCase(criteria)

        coVerify(exactly = 1) { cityRepository.getCityByCreiteria(criteria) }

        assertTrue(result.isSuccess)
        assertEquals(mockFilteredCities, result.getOrNull())
        assertEquals(1, result.getOrNull()?.size)
    }

    @Test
    fun `should return a Result failure when repository fails`() = runTest {
        val criteria = "Error"
        val errorMessage = "Database Error"
        val exception = RuntimeException(errorMessage)
        coEvery { cityRepository.getCityByCreiteria(criteria) } returns Result.failure(exception)

        val result = getCitiesByCriteriaUseCase(criteria)

        coVerify(exactly = 1) { cityRepository.getCityByCreiteria(criteria) }

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}

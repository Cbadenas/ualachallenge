package com.ualachallenge.domain.city

import com.ualachallenge.ports.CityRepositoryPort

class GetAllCitiesUseCase(private val repo: CityRepositoryPort) {

    suspend operator fun invoke(criteria: String): Result<List<City>> = repo.getCities()

}
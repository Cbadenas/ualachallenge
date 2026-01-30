package com.ualachallenge.domain.city

import com.ualachallenge.ports.CityRepositoryPort

class GetAllCitiesUseCase(private val repo: CityRepositoryPort) {

    suspend operator fun invoke(): Result<List<City>> = repo.getCities()

}
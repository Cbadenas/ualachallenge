package com.ualachallenge.domain.city

import com.ualachallenge.ports.CityRepositoryPort

class GetCitiesByCriteriaUsecase(private val repo: CityRepositoryPort) {

    suspend operator fun invoke(criteria: String) = repo.getCityByCreiteria(criteria)

}
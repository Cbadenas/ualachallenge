package com.ualachallenge.domain.city

import com.ualachallenge.ports.CityRepositoryPort

class RemoveFavoritedCityUsecase(
    private val repo: CityRepositoryPort
) {

    suspend operator fun invoke(cityId: Int) = repo.removeFavoriteCity(cityId)

}
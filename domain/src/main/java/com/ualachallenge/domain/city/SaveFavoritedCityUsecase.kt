package com.ualachallenge.domain.city

import com.ualachallenge.ports.CityRepositoryPort

class SaveFavoritedCityUsecase(
    private val repo: CityRepositoryPort
) {

    suspend operator fun invoke(cityId: Int) = repo.saveFavoriteCity(cityId)

}
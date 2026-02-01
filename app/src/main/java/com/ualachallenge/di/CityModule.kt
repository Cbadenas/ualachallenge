package com.ualachallenge.di

import com.ualachallenge.domain.city.GetAllCitiesUseCase
import com.ualachallenge.domain.city.GetCitiesByCriteriaUsecase
import com.ualachallenge.domain.city.RemoveFavoritedCityUsecase
import com.ualachallenge.domain.city.SaveFavoritedCityUsecase
import com.ualachallenge.ports.CityRepositoryPort
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CityModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
            coerceInputValues = true
        }
    }

    @Provides fun provideCitiesUsecase(repo: CityRepositoryPort) = GetAllCitiesUseCase(repo)

    @Provides fun provideSearchByCriteriaCitiesUsecase(repo: CityRepositoryPort) =
        GetCitiesByCriteriaUsecase(repo)

    @Provides fun providesaveFavoriteCityUseCase(repo: CityRepositoryPort) =
        SaveFavoritedCityUsecase(repo)

    @Provides fun provideremoveFavoriteCityUseCase(repo: CityRepositoryPort) =
        RemoveFavoritedCityUsecase(repo)


}
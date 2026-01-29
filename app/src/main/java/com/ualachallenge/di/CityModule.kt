package com.ualachallenge.di

import com.ualachallenge.network.adapters.CityRepositoryAdapter
import com.ualachallenge.ports.CityRepositoryPort
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CityModule {

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

    @Binds
    @Singleton
    abstract fun bindCityRepository(impl: CityRepositoryAdapter): CityRepositoryPort

}
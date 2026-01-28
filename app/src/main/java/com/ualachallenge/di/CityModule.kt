package com.ualachallenge.di

import com.ualachallenge.network.adapters.CityRepositoryAdapter
import com.ualachallenge.ports.CityRepositoryPort
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CityModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: CityRepositoryAdapter): CityRepositoryPort

}
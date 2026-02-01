package com.ualachallenge.common.di

import android.content.Context
import androidx.room.Room
import com.ualachallenge.common.db.FavoriteCityDao
import com.ualachallenge.common.db.UalaChallengeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUalaChallengeDatabase(@ApplicationContext context: Context): UalaChallengeDatabase {
        return Room.databaseBuilder(
            context,
            UalaChallengeDatabase::class.java,
            "uala_challenge_database" // Nombre del archivo de la base de datos
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteCityDao(database: UalaChallengeDatabase): FavoriteCityDao {
        return database.favoriteCityDao()
    }
}
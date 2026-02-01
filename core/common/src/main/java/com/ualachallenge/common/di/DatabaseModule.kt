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

    /**
     * Proporciona una instancia Singleton de la base de datos Room.
     * Hilt necesita el Context de la aplicación para construir la base de datos.
     */
    @Provides
    @Singleton
    fun provideUalaChallengeDatabase(@ApplicationContext context: Context): UalaChallengeDatabase {
        return Room.databaseBuilder(
            context,
            UalaChallengeDatabase::class.java,
            "uala_challenge_database" // Nombre del archivo de la base de datos
        ).build()
    }

    /**
     * Proporciona una instancia del DAO a partir de la base de datos.
     * Hilt sabe cómo crear la base de datos (del método de arriba) y la usa para proveer el DAO.
     */
    @Provides
    @Singleton
    fun provideFavoriteCityDao(database: UalaChallengeDatabase): FavoriteCityDao {
        return database.favoriteCityDao()
    }
}
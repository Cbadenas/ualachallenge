package com.ualachallenge.common.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object para la tabla de ciudades favoritas.
 * Define las operaciones de base de datos permitidas.
 */
@Dao
interface FavoriteCityDao {

    /**
     * Inserta una ciudad favorita. Si ya existe, no hace nada.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favoriteCity: FavoriteCityEntity)

    /**
     * Elimina una ciudad de la lista de favoritos.
     */
    @Delete
    suspend fun removeFavorite(favoriteCity: FavoriteCityEntity)

    /**
     * Obtiene todos los IDs de las ciudades favoritas como un Flow.
     * Flow permitirá que la UI se actualice automáticamente cuando cambien los datos.
     */
    @Query("SELECT cityId FROM favorite_cities")
    fun getAllFavoriteIds(): Flow<List<Int>>

    /**
     * Comprueba si una ciudad específica es favorita.
     * Devuelve 1 (true) si existe, 0 (false) si no.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_cities WHERE cityId = :cityId)")
    suspend fun isFavorite(cityId: Int): Boolean
}

package com.ualachallenge.common.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favoriteCity: FavoriteCityEntity)

    @Delete
    suspend fun removeFavorite(favoriteCity: FavoriteCityEntity)

    @Query("SELECT cityId FROM favorite_cities")
    fun getAllFavoriteIds(): Flow<List<Int>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_cities WHERE cityId = :cityId)")
    suspend fun isFavorite(cityId: Int): Boolean
}

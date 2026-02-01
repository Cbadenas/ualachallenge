package com.ualachallenge.common.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa la tabla 'favorite_cities' en la base de datos.
 * Solo almacena el ID de la ciudad marcada como favorita.
 *
 * @param cityId El ID de la ciudad (viene del JSON o API), actúa como clave primaria.
 */
@Entity(tableName = "favorite_cities")
data class FavoriteCityEntity(
    @PrimaryKey
    val cityId: Int
)
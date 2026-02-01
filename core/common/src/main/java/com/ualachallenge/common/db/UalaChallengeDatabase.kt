package com.ualachallenge.common.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteCityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UalaChallengeDatabase : RoomDatabase() {

    abstract fun favoriteCityDao(): FavoriteCityDao

}

package com.visca.subgithub.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavDao {
    @Query("SELECT * FROM favorite_users ORDER BY username DESC")
    fun getFavorites(): LiveData<List<FavEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavEntity)

    @Delete
    fun deleteFavorite(favorite: FavEntity)
}
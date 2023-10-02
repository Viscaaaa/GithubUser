package com.visca.subgithub.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavDao {
    @Query("SELECT * FROM favorite_users")
    fun getFavorites(): LiveData<List<FavEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(favorite: FavEntity)

    @Query("DELETE FROM favorite_users WHERE favorite_users.id = :id")
    fun deleteFavorite(id : Int)

    @Query("SELECT EXISTS(SELECT * FROM favorite_users WHERE favorite_users.id = :id)")
    fun isUserFav(id: Int): Int


}
package com.visca.subgithub.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.visca.subgithub.database.FavDao
import com.visca.subgithub.database.FavEntity
import com.visca.subgithub.database.FavRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavRepository (application: Application) {
    private val mFavoriteDao: FavDao

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favDao()
    }

    fun getAllFavorite(): LiveData<List<FavEntity>> = mFavoriteDao.getFavorites()

    fun insert(favorite: FavEntity) {
        executorService.execute { mFavoriteDao.insertFavorite(favorite) }
    }

    fun delete(favorite: FavEntity) {
        executorService.execute { mFavoriteDao.deleteFavorite(favorite) }
    }
}
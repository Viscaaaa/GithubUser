package com.visca.subgithub

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.visca.subgithub.data.ResponseDetail
import com.visca.subgithub.database.FavEntity
import com.visca.subgithub.repository.FavRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavViewModel (application: Application): ViewModel() {
    private val mFavoriteRepository: FavRepository = FavRepository(application)
    private val _thisFavorite = MutableLiveData<Boolean>()
    val thisFavorite : LiveData<Boolean> = _thisFavorite
    private val _user = MutableLiveData<ResponseDetail?>()
    val user: LiveData<ResponseDetail?> = _user
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()


    fun getAllFavorite(): LiveData<List<FavEntity>> {
        return mFavoriteRepository.getAllFavorite()
    }


    fun checkFavorite(id : Int) = mFavoriteRepository.checkFavorite(id)

    fun insert(user : FavEntity ){
        mFavoriteRepository.insert(user)
    }

    fun delete(id: Int) {
        mFavoriteRepository.delete(id)
    }

    }


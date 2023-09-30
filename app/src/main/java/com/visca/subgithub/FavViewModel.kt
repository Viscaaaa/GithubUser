package com.visca.subgithub

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.visca.subgithub.data.ResponseDetail
import com.visca.subgithub.database.FavEntity
import com.visca.subgithub.repository.FavRepository

class FavViewModel (application: Application): ViewModel() {
    private val mFavoriteRepository: FavRepository = FavRepository(application)
    private val _thisFavorite = MutableLiveData<Boolean>()
    val thisFavorite : LiveData<Boolean> = _thisFavorite
    private val _user = MutableLiveData<ResponseDetail?>()
    val user: LiveData<ResponseDetail?> = _user

    fun getAllFavorite(): LiveData<List<FavEntity>> = mFavoriteRepository.getAllFavorite()


    fun setThisFavorite(thisFavorite:Boolean){
        _thisFavorite.value = thisFavorite

    }

    fun insertFavorite(thisFavorite: FavEntity){
        setThisFavorite(true)
        mFavoriteRepository.insert(thisFavorite)
    }

    fun deleteFavorite(thisFavorite: FavEntity){
        setThisFavorite(false)
        mFavoriteRepository.delete(thisFavorite)

    }

    fun  updateFavorite(favorited: FavEntity){
        if (thisFavorite.value != true){
            insertFavorite(favorited)
        } else {
            deleteFavorite(favorited)
        }
    }
}
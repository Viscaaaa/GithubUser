package com.visca.subgithub

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.visca.subgithub.data.ApiConfig
import com.visca.subgithub.data.ResponseDetail
import com.visca.subgithub.repository.FavRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {


    private val _user = MutableLiveData<ResponseDetail?>()
    val user: LiveData<ResponseDetail?> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userFollower = MutableLiveData<List<ResponseDetail>?>()
    val userFollower: LiveData<List<ResponseDetail>?> = _userFollower

    private val _userFollowing = MutableLiveData<List<ResponseDetail>?>()
    val userFollowing: LiveData<List<ResponseDetail>?> = _userFollowing

    companion object {
        private const val TAG = "UserDetailViewModel"
    }


    fun getDetailUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<ResponseDetail> {
            override fun onResponse(
                call: Call<ResponseDetail>,
                response: Response<ResponseDetail>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _user.value = responseBody
                        Log.d(TAG, responseBody.toString())
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    Log.e(TAG, "API request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFollowerUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowers(username)
        client.enqueue(object : Callback<List<ResponseDetail>> {
            override fun onResponse(
                call: Call<List<ResponseDetail>>,
                response: Response<List<ResponseDetail>>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _userFollower.value = responseBody
                        Log.d("FOLLOWER", responseBody.toString())
                    }
                } else {
                    _isLoading.value = true
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<List<ResponseDetail>>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "${t.message}")
            }
        })
    }

    fun getFollowingUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowing(username)
        client.enqueue(object : Callback<List<ResponseDetail>> {
            override fun onResponse(
                call: Call<List<ResponseDetail>>,
                response: Response<List<ResponseDetail>>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _userFollowing.value = responseBody
                        Log.d("FOLLOWING", responseBody.toString())
                    }
                } else {
                    _isLoading.value = true
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<List<ResponseDetail>>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "${t.message}")
            }
        })
    }
}
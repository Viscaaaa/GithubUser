package com.visca.subgithub.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("search/users")
    fun getUsers(
        @Query("q") username: String
    ): Call<ResponseUser>

    @GET("users/{username}")
    fun getDetailUser(@Path("username")username: String): Call<ResponseDetail>

    @GET("users/{username}/followers")
    fun getUserFollowers(@Path("username") username: String): Call<List<ResponseDetail>>

    @GET("users/{username}/following")
    fun getUserFollowing(@Path("username") username: String): Call<List<ResponseDetail>>
}
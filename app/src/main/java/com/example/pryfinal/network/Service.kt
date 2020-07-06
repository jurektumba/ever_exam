package com.example.pryfinal.network

import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("posts")
    suspend fun getPosts() :Response<List<PostsResponse>>

    @GET("profile")
    suspend fun getProfile() :Response<UserResponse>

    @GET("users")
    suspend fun getFriends() :Response<List<UserResponse>>
}
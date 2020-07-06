package com.example.pryfinal.network

data class PostsResponse(
    val id: Int,
    val user_id: Int,
    val username: String,
    val user_image: String,
    val body: String,
    val image: String,
    val likes: Int,
    val comment: Any,
    val descripcion: String,
    val objSocial: Social
)

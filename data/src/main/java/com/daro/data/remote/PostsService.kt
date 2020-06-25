package com.daro.data.remote

import com.daro.data.remote.entities.PostDTO
import retrofit2.http.*

interface PostsService {

    @GET("posts")
    suspend fun getPosts(): List<PostDTO>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): PostDTO

    @POST("posts")
    suspend fun createPost(@Body post: PostDTO)

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") postId: Int)

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") postId: Int, @Body post: PostDTO)
}
package com.daro.data.remote

import com.daro.data.remote.entities.PostResponse
import retrofit2.http.*

interface PostsService {

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): PostResponse

    @POST("posts")
    suspend fun createPost(@Body post: PostResponse): PostResponse

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") postId: Int)

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") postId: Int, @Body post: PostResponse)
}
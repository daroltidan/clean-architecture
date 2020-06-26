package com.daro.data.repositories.source

import com.daro.data.local.entities.PostEntity

interface PostsDataSource {
    suspend fun getPosts(): List<PostEntity>

    suspend fun getPost(id: Int): PostEntity
}
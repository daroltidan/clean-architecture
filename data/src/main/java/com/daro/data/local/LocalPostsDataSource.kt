package com.daro.data.local

import com.daro.data.local.dao.PostsDao
import com.daro.data.local.entities.PostEntity
import com.daro.data.repositories.source.PostsDataSource

class LocalPostsDataSource(private val postsDao: PostsDao) : PostsDataSource {
    override suspend fun getPosts() = postsDao.getAll()
    override suspend fun getPost(id: Int) = postsDao.getPost(id)
    override suspend fun createPost(post: PostEntity) = postsDao.save(post)
}
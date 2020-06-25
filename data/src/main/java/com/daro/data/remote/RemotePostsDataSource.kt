package com.daro.data.remote

import com.daro.data.local.dao.PostsDao
import com.daro.data.local.entities.PostEntity
import com.daro.data.mappers.PostNetworkToEntityMapper
import com.daro.data.repositories.source.PostsDataSource

class RemotePostsDataSource(
    private val postsDao: PostsDao,
    private val service: PostsService,
    private val mapper: PostNetworkToEntityMapper
) : PostsDataSource {
    override suspend fun getPosts(): List<PostEntity> {
        val remotePosts = service.getPosts()
        val localPosts = remotePosts.map(mapper::map)
        postsDao.saveAll(localPosts)
        return postsDao.getAll()
    }
}
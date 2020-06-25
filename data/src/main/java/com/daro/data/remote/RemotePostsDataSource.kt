package com.daro.data.remote

import com.daro.data.local.dao.PostsDao
import com.daro.data.local.entities.PostEntity
import com.daro.data.mappers.PostNetworkToEntityMapper
import com.daro.data.repositories.source.PostsDataSource

class RemotePostsDataSource(
    private val postsDao: PostsDao,
    private val retrofitClient: RetrofitClient,
    private val postNetworkToEntityMapper: PostNetworkToEntityMapper
) : PostsDataSource {
    override suspend fun getPosts(): List<PostEntity> {
        val service = retrofitClient.getPostsService()
        val remotePosts = service.getPosts()
        val localPosts = remotePosts.map(postNetworkToEntityMapper::map)
        postsDao.saveAll(localPosts)
        return postsDao.getAll()
    }
}
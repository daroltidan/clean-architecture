package com.daro.data.remote

import com.daro.data.local.entities.PostEntity
import com.daro.data.mappers.PostNetworkToEntityMapper
import com.daro.data.repositories.source.PostsDataSource

class RemotePostsDataSource(
    private val service: PostsService,
    private val mapper: PostNetworkToEntityMapper
) : PostsDataSource {
    override suspend fun getPosts(): List<PostEntity> {
        val remotePosts = service.getPosts()
        return remotePosts.map(mapper::map)
    }

    override suspend fun getPost(id: Int): PostEntity {
        val post = service.getPost(id)
        return mapper.map(post)
    }

    override suspend fun createPost(post: PostEntity) {
        service.createPost(mapper.reverseMap(post))
    }
}
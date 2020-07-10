package com.daro.data.repositories

import com.daro.data.mappers.PostDomainMapper
import com.daro.data.repositories.source.PostsDataSource
import com.daro.domain.entities.Post
import com.daro.domain.repositories.Repository

class PostsRepositoryImpl(
    private val localPostsDataSource: PostsDataSource,
    private val remotePostsDataSource: PostsDataSource,
    private val entityMapper: PostDomainMapper
) : Repository<Post> {


    override suspend fun get(forceUpdate: Boolean): List<Post> {
        val items = if (!forceUpdate) {
            localPostsDataSource.getPosts()
        } else {
            val remoteData = remotePostsDataSource.getPosts()
            remoteData.onEach { localPostsDataSource.createPost(it) }
            localPostsDataSource.getPosts()
        }

        return items.map(entityMapper::map)
    }

    override suspend fun get(id: Int): Post {
        val post = localPostsDataSource.getPost(id)
        return entityMapper.map(post)
    }

    override suspend fun create(t: Post) {
        val localPost = entityMapper.reverseMap(t)
        localPostsDataSource.createPost(localPost)
        remotePostsDataSource.createPost(localPost)
    }
}
package com.daro.data.repositories

import com.daro.data.mappers.PostEntityToDomainMapper
import com.daro.data.repositories.source.PostsDataSourceFactory
import com.daro.domain.entities.Post
import com.daro.domain.repositories.Repository

class PostsRepositoryImpl(
    private val factory: PostsDataSourceFactory,
    private val entityMapper: PostEntityToDomainMapper
) : Repository<Post> {

    override suspend fun get(forceUpdate: Boolean): List<Post> {
        val dataSource = factory.getDataSource(forceUpdate)
        return dataSource.getPosts().map(entityMapper::map)
    }
}
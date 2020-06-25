package com.daro.data.repositories

import com.daro.data.mappers.PostEntityToDomain
import com.daro.data.repositories.source.PostsDataSourceFactory
import com.daro.domain.entities.Post
import com.daro.domain.error.ErrorHandler
import com.daro.domain.repositories.Repository

class PostsRepository(
    private val factory: PostsDataSourceFactory,
    private val errorHandler: ErrorHandler,
    private val entityMapper: PostEntityToDomain
) : Repository<Post> {

    override suspend fun get(forceUpdate: Boolean): List<Post> {
        val dataSource = factory.getDataSource(forceUpdate)
        return dataSource.getPosts().map(entityMapper::map)
    }
}
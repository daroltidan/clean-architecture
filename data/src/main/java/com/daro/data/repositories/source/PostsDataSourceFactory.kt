package com.daro.data.repositories.source

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsDataSourceFactory(
    private val localSource: PostsDataSource,
    private val remoteSource: PostsDataSource
) {

    suspend fun getDataSource(forceUpdate: Boolean) = withContext(Dispatchers.IO) {
        if (!forceUpdate) {
            val localData = localSource.getPosts()
            if (localData.isEmpty()) {
                return@withContext remoteSource
            }
            return@withContext localSource
        }
        return@withContext remoteSource
    }
}
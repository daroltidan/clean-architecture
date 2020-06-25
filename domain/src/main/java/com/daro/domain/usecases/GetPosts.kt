package com.daro.domain.usecases

import com.daro.domain.entities.Post
import com.daro.domain.repositories.Repository

class GetPosts(private val repository: Repository<Post>) {

    suspend fun invoke(forceUpdate: Boolean) = repository.get(forceUpdate)

}
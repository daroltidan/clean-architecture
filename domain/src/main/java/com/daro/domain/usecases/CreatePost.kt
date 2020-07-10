package com.daro.domain.usecases

import com.daro.domain.entities.Post
import com.daro.domain.repositories.Repository
import kotlin.random.Random

class CreatePost constructor(private val repository: Repository<Post>) {

    suspend fun invoke(title: String, body: String) {
        val post = Post(Random.nextInt(0, 100), 1, title, body)
        repository.create(post)
    }
}
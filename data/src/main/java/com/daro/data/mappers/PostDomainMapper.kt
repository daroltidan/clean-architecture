package com.daro.data.mappers

import com.daro.data.local.entities.PostEntity
import com.daro.domain.entities.Post
import com.daro.domain.mappers.Mapper

class PostDomainMapper : Mapper<PostEntity, Post>() {
    override fun map(i: PostEntity) = Post(i.id, i.userId, i.title, i.body)

    fun reverseMap(post: Post) = PostEntity(post.id, post.userId, post.title, post.body)
}
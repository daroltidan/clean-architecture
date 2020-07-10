package com.daro.data.mappers

import com.daro.data.local.entities.PostEntity
import com.daro.data.remote.entities.PostResponse
import com.daro.domain.mappers.Mapper

class PostNetworkToEntityMapper : Mapper<PostResponse, PostEntity>() {
    override fun map(i: PostResponse) = PostEntity(i.id, i.userId, i.title, i.body)

    fun reverseMap(postEntity: PostEntity) =
        PostResponse(
            postEntity.id,
            postEntity.userId,
            postEntity.title,
            postEntity.body
        )
}
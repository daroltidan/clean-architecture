package com.daro.data.mappers

import com.daro.data.local.entities.PostEntity
import com.daro.data.remote.entities.PostDTO
import com.daro.domain.mappers.Mapper

class PostNetworkToEntityMapper : Mapper<PostDTO, PostEntity>() {
    override fun map(i: PostDTO) =
        PostEntity(i.id, i.userId, i.title, i.body)
}
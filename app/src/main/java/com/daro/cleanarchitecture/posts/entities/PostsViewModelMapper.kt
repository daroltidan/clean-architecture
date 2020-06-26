package com.daro.cleanarchitecture.posts.entities

import com.daro.domain.entities.Post
import com.daro.domain.mappers.Mapper

class PostsViewModelMapper : Mapper<Post, PostViewModel>() {
    override fun map(i: Post) = PostViewModel(i.id, i.title, i.body)
}
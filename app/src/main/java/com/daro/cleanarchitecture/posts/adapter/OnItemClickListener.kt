package com.daro.cleanarchitecture.posts.adapter

import com.daro.cleanarchitecture.posts.entities.PostViewModel

interface OnItemClickListener {

    fun onItemClicked(post: PostViewModel)
}
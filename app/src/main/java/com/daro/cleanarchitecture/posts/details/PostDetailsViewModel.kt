package com.daro.cleanarchitecture.posts.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daro.cleanarchitecture.posts.entities.PostViewModel
import com.daro.cleanarchitecture.posts.entities.PostsViewModelMapper
import com.daro.domain.usecases.GetPosts
import kotlinx.coroutines.launch

class PostDetailsViewModel(
    private val getPosts: GetPosts,
    private val mapper: PostsViewModelMapper,
    private val arguments: DetailsFragmentArgs
) : ViewModel() {

    private val post = MutableLiveData<PostViewModel>()
    fun getPost(): LiveData<PostViewModel> = post

    init {
        retrievePost()
    }

    private fun retrievePost() {
        viewModelScope.launch {
            post.value = mapper.map(getPosts.getById(arguments.postId))
        }
    }

}
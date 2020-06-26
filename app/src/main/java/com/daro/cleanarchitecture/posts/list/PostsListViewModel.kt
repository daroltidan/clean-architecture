package com.daro.cleanarchitecture.posts.list

import androidx.lifecycle.*
import com.daro.cleanarchitecture.posts.entities.PostViewModel
import com.daro.cleanarchitecture.posts.entities.PostsViewModelMapper
import com.daro.domain.entities.ResultState
import com.daro.domain.error.ErrorHandler
import com.daro.domain.usecases.GetPosts
import kotlinx.coroutines.launch

private const val VIEW_STATE = "view_state"

class PostsViewModel(
    private val postsViewModelMapper: PostsViewModelMapper,
    private val getPosts: GetPosts,
    private val savedStateHandle: SavedStateHandle,
    private val errorHandlerImpl: ErrorHandler
) : ViewModel() {

    private val posts = MutableLiveData<ResultState<List<PostViewModel>>>()
    fun getPosts(): LiveData<ResultState<List<PostViewModel>>> = posts

    init {
        savedStateHandle.get<List<PostViewModel>>(VIEW_STATE)?.let {
            posts.value = ResultState.SuccessState(it)
        } ?: run { retrievePosts() }
    }

    fun retrievePosts(forceUpdate: Boolean = false) {
        posts.value = ResultState.LoadingState

        viewModelScope.launch {
            posts.value = try {
                val posts = getPosts.invoke(forceUpdate).map(postsViewModelMapper::map)
                savedStateHandle.set(VIEW_STATE, posts)
                ResultState.SuccessState(posts)
            } catch (e: Exception) {
                ResultState.ErrorState(errorHandlerImpl.handleError(e))
            }
        }
    }
}
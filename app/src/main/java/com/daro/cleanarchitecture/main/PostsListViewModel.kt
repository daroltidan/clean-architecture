package com.daro.cleanarchitecture.main

import androidx.lifecycle.*
import com.daro.cleanarchitecture.main.entities.PostViewModel
import com.daro.cleanarchitecture.main.entities.PostsViewModelMapper
import com.daro.domain.entities.ResultState
import com.daro.domain.error.ErrorHandler
import com.daro.domain.usecases.GetPosts
import kotlinx.coroutines.launch

private const val VIEW_STATE = "view_state"

class MainViewModel(
    private val postsViewModelMapper: PostsViewModelMapper,
    private val getPosts: GetPosts,
    savedStateHandle: SavedStateHandle,
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
                ResultState.SuccessState(posts)
            } catch (e: Exception) {
                ResultState.ErrorState(errorHandlerImpl.handleError(e))
            }
        }
    }
}
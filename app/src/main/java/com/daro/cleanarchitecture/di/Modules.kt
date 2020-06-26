package com.daro.cleanarchitecture.di

import androidx.lifecycle.SavedStateHandle
import com.daro.cleanarchitecture.posts.details.DetailsFragmentArgs
import com.daro.cleanarchitecture.posts.details.PostDetailsViewModel
import com.daro.cleanarchitecture.posts.entities.PostsViewModelMapper
import com.daro.cleanarchitecture.posts.list.PostsViewModel
import com.daro.data.error.ErrorHandlerImpl
import com.daro.data.repositories.PostsRepositoryImpl
import com.daro.domain.usecases.GetPosts
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModule = module {

    //usecases
    factory {
        val repository: PostsRepositoryImpl = get()
        GetPosts(repository)
    }
}

val presentationModule = module {

    //mappers
    single { PostsViewModelMapper() }

    //viewmodels
    viewModel { (savedStateHandle: SavedStateHandle) ->
        val getPosts: GetPosts = get()
        val errorHandler: ErrorHandlerImpl = get()
        val mapper: PostsViewModelMapper = get()
        PostsViewModel(
            mapper,
            getPosts,
            savedStateHandle,
            errorHandler
        )
    }

    viewModel { (navArgs: DetailsFragmentArgs) ->
        val getPosts: GetPosts = get()
        val mapper: PostsViewModelMapper = get()
        PostDetailsViewModel(getPosts, mapper, navArgs)
    }
}
package com.daro.cleanarchitecture.di

import androidx.lifecycle.SavedStateHandle
import com.daro.cleanarchitecture.main.MainViewModel
import com.daro.cleanarchitecture.main.entities.PostsViewModelMapper
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
        MainViewModel(mapper, getPosts, savedStateHandle, errorHandler)
    }
}
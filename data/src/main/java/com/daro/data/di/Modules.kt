package com.daro.data.di

import com.daro.data.error.ErrorHandlerImpl
import com.daro.data.local.LocalPostsDataSource
import com.daro.data.local.PostsDatabase
import com.daro.data.local.dao.PostsDao
import com.daro.data.mappers.PostDomainMapper
import com.daro.data.mappers.PostNetworkToEntityMapper
import com.daro.data.remote.PostsService
import com.daro.data.remote.RemotePostsDataSource
import com.daro.data.remote.RetrofitClient
import com.daro.data.repositories.PostsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val LOCAL = "local"
private const val REMOTE = "remote"

val dataModule = module {
    //mappers
    single { PostDomainMapper() }
    single { PostNetworkToEntityMapper() }

    //local
    single { PostsDatabase.getInstance(androidContext()) }
    single {
        val db: PostsDatabase = get()
        db.postsDao()
    }

    //network
    single { RetrofitClient() }
    single {
        val retrofitClient: RetrofitClient = get()
        retrofitClient.getPostsService()
    }

    //repository
    single {
        val dao: PostsDao = get()
        LocalPostsDataSource(dao)
    }
    single {
        val dao: PostsDao = get()
        val postsService: PostsService = get()
        val postNetworkToEntityMapper: PostNetworkToEntityMapper = get()
        RemotePostsDataSource(postsService, postNetworkToEntityMapper)
    }

    single {
        val localPostsDataSource: LocalPostsDataSource = get()
        val remotePostsDataSource: RemotePostsDataSource = get()
        val mapper: PostDomainMapper = get()
        PostsRepositoryImpl(localPostsDataSource, remotePostsDataSource, mapper)
    }

    //others
    single { ErrorHandlerImpl() }

}
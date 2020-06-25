package com.daro.cleanarchitecture

import android.app.Application
import com.daro.cleanarchitecture.di.domainModule
import com.daro.cleanarchitecture.di.presentationModule
import com.daro.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PostsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@PostsApp)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}
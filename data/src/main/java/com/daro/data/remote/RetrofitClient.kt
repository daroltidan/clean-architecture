package com.daro.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class RetrofitClient {

    private val gson = GsonBuilder().create()
    private val logger =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    private val client = OkHttpClient.Builder().apply { addInterceptor(logger) }

    private fun <T> getService(tService: Class<T>): T {
        return createRetrofitClient().create(tService)
    }

    fun getPostsService() = getService(PostsService::class.java)

    private fun createRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .apply {
                baseUrl(BASE_URL)
                client(client.build())
                addConverterFactory(GsonConverterFactory.create(gson))
            }
            .build()
    }
}
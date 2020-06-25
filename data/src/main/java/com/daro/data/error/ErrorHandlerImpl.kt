package com.daro.data.error

import com.daro.domain.entities.Error
import com.daro.domain.error.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler {
    override fun handleError(throwable: Throwable) = when (throwable) {
        is IOException -> Error.Network
        is HttpException -> {
            when (throwable.code()) {
                HttpURLConnection.HTTP_NOT_FOUND, HttpURLConnection.HTTP_BAD_REQUEST -> Error.NotFound
                in HttpURLConnection.HTTP_INTERNAL_ERROR..HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> Error.ServiceUnavailable
                else -> Error.Unknown
            }
        }
        else -> Error.Unknown
    }
}
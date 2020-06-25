package com.daro.domain.error

interface ErrorHandler {
    fun getError(throwable: Throwable): Error
}
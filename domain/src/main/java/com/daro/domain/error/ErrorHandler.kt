package com.daro.domain.error

import com.daro.domain.entities.Error

interface ErrorHandler {
    fun handleError(throwable: Throwable): Error
}
package com.daro.domain.error

sealed class Error {
    object Network : Error()
    object ServiceUnavailable : Error()
    object NotFound : Error()
    object Unknown : Error()
}
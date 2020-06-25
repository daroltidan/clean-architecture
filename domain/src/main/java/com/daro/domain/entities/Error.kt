package com.daro.domain.entities

sealed class Error {
    object Network : Error()
    object ServiceUnavailable : Error()
    object NotFound : Error()
    object Unknown : Error()
}
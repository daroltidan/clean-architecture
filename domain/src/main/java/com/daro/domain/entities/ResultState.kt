package com.daro.domain.entities

sealed class ResultState<out R> {

    data class SuccessState<out T>(val data: T) : ResultState<T>()
    data class ErrorState(val error: Error) : ResultState<Nothing>()
    object LoadingState : ResultState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is SuccessState<*> -> "Success[data=$data] State"
            is ErrorState -> "Error[exception=$error] State"
            is LoadingState -> "Loading state"
        }
    }
}
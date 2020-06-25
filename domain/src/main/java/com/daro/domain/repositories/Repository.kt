package com.daro.domain.repositories

interface Repository<T> {
    suspend fun get(forceUpdate: Boolean): List<T>
}
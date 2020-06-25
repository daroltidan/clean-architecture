package com.daro.domain.repositories

interface Repository<T> {
    fun create(t: T)
    fun get(): List<T>
    fun get(id: String): T
    fun update(t: T)
    fun delete(id: String)
    fun delete(ids: List<String>)
}
package com.daro.domain.mappers

abstract class Mapper<I, O> {
    abstract fun map(i: I): O
}
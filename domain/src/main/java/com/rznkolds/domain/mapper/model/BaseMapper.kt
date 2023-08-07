package com.rznkolds.domain.mapper.model

interface BaseMapper<I, O> {
    fun map(input: I): O
}
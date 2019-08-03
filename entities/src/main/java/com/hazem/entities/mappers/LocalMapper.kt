package com.hazem.entities.mappers

interface LocalMapper<From, To> {

    fun convert(from: From): To
}
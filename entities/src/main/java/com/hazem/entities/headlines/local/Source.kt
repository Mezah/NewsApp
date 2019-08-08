package com.hazem.entities.headlines.local

data class Source(val id: String?, val name: String?) {
    override fun toString(): String {
        return name ?: ""
    }
}
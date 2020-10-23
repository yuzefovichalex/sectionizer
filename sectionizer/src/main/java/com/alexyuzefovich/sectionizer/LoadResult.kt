package com.alexyuzefovich.sectionizer

sealed class LoadResult<out T> {
    data class Success<out T>(val items: List<T>) : LoadResult<T>()
    data class Error(val throwable: Throwable?) : LoadResult<Nothing>()
}
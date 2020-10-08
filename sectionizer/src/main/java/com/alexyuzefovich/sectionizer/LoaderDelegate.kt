package com.alexyuzefovich.sectionizer

abstract class LoaderDelegate<T, P : LoadParams> {

    abstract val params: P

    abstract fun loadData(): LoadResult<T>

}
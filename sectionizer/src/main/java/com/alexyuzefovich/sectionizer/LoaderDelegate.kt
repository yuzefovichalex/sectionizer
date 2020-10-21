package com.alexyuzefovich.sectionizer

import kotlinx.coroutines.CoroutineScope

abstract class LoaderDelegate<T, P : LoadParams>(
    val coroutineScope: CoroutineScope
) {

    abstract val params: P

    abstract fun loadData(): LoadResult<T>

}
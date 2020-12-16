package com.alexyuzefovich.sectionizer

import kotlinx.coroutines.CoroutineScope

abstract class SectionDataLoader<T, P : LoadParams>(
    val coroutineScope: CoroutineScope
) {

    abstract val params: P

    abstract suspend fun loadData(): LoadResult<T>

}
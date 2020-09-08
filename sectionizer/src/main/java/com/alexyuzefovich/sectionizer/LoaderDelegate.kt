package com.alexyuzefovich.sectionizer

import kotlinx.coroutines.CoroutineScope

abstract class LoaderDelegate<T, P>(
    private val coroutineScope: CoroutineScope
) {

    abstract val params: P

    fun loadData(
        successAction: (List<T>) -> Unit,
        failureAction: (Throwable) -> Unit
    ) {

    }

}
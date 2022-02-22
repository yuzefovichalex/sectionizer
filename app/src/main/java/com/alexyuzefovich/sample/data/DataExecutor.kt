package com.alexyuzefovich.sample.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DataExecutor<T>(
    private val dataListener: DataListener<T>,
    private val coroutineScope: CoroutineScope,
    private val loadDataAction: suspend () -> List<T>
) {

    fun execute() {
        coroutineScope.launch {
            val data = loadDataAction()
            dataListener.onDataReady(data)
        }
    }

}
package com.alexyuzefovich.sample.data

interface DataListener<T> {
    fun onDataReady(data: List<T>)
}
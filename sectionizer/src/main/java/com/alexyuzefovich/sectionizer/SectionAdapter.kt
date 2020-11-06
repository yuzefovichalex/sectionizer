package com.alexyuzefovich.sectionizer

interface SectionAdapter<T> {

    fun getData(): List<T>
    fun submitData(items: List<T>)

}
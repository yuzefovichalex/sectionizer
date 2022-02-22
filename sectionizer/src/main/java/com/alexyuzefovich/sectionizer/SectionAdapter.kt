package com.alexyuzefovich.sectionizer

interface SectionAdapter<T> {
    fun restoreFromSnapshot(snapshot: List<T>)
    fun getLatestSnapshot(): List<T>
}
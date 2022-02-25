package com.alexyuzefovich.sectionizer

/**
 * Interface that helps to restore data in [Section] when [Section.SWAP] policy is selected.
 *
 * @author Alexander Yuzefovich
 * */
interface SectionAdapter<T> {
    /**
     * Describe how to set (restore) data inside adapter. For example, for [androidx.recyclerview.widget.ListAdapter]
     * it can be call of [androidx.recyclerview.widget.ListAdapter.submitList] method.
     * */
    fun restoreFromSnapshot(snapshot: List<T>)

    /**
     * Get the latest data stored by this adapter. For example, for [androidx.recyclerview.widget.ListAdapter]
     * we can get the latest data via [androidx.recyclerview.widget.ListAdapter.getCurrentList] method.
     *
     * @return The latest stored data.
     * */
    fun getLatestSnapshot(): List<T>
}
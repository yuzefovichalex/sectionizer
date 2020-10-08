package com.alexyuzefovich.sectionizer

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class Section<T, A>
        where A : RecyclerView.Adapter<*>,
              A : SectionAdapter<T>
{

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Section<*, *>>() {
        override fun areItemsTheSame(oldItem: Section<*, *>, newItem: Section<*, *>): Boolean {
            return oldItem.isTheSameWith(newItem)
        }

        override fun areContentsTheSame(oldItem: Section<*, *>, newItem: Section<*, *>): Boolean {
            return true
        }
    }

    abstract val adapter: A

    abstract val loaderDelegate: LoaderDelegate<T, *>

    abstract fun isTheSameWith(another: Section<*, *>): Boolean

    fun loadInto(rv: RecyclerView) {
        rv.adapter = adapter
        val result = loaderDelegate.loadData()
        adapter.submitData(result.items)
    }

}
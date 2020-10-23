package com.alexyuzefovich.sectionizer

import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

abstract class Section<T, A>
        where A : RecyclerView.Adapter<*>,
              A : SectionAdapter<T>
{

    interface LoadCallback {
        fun onLoadStart()
        fun onLoadSuccess()
        fun onLoadError(throwable: Throwable?)
    }


    abstract val adapter: A

    abstract val sectionDataLoader: SectionDataLoader<T, *>

    abstract fun isTheSameWith(another: Section<*, *>): Boolean

    internal fun loadInto(viewHolder: SectionsAdapter.ViewHolder<*>) {
        with(viewHolder) {
            sectionRV.adapter = adapter
            loadCallback?.onLoadStart()
            sectionDataLoader.coroutineScope.launch {
                when (val result = sectionDataLoader.loadData()) {
                    is LoadResult.Success -> {
                        loadCallback?.onLoadSuccess()
                        adapter.submitData(result.items)
                    }
                    is LoadResult.Error -> loadCallback?.onLoadError(result.throwable)
                }
            }
        }
    }

}
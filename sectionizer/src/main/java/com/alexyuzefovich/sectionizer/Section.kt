package com.alexyuzefovich.sectionizer

import androidx.annotation.IntDef
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

abstract class Section<T, A>(
    @AdapterPolicy
    private val adapterPolicy: Int = SWAP
)
        where A : RecyclerView.Adapter<*>,
              A : SectionAdapter<T>
{

    interface LoadCallback {
        fun onLoadStart()
        fun onLoadSuccess()
        fun onLoadError(throwable: Throwable?)
    }

    companion object {
        const val RESET = 0
        const val SWAP = 1
    }

    @IntDef(RESET, SWAP)
    @Retention(AnnotationRetention.SOURCE)
    annotation class AdapterPolicy


    abstract val adapter: A

    abstract val sectionDataLoader: SectionDataLoader<T, *>

    abstract fun isTheSameWith(another: Section<*, *>): Boolean

    abstract fun isContentTheSameWith(another: Section<*, *>): Boolean

    internal fun loadInto(viewHolder: SectionsAdapter.ViewHolder<*>) {
        with(viewHolder) {
            val previousAdapter = sectionRV.adapter
            // If new RecyclerView already has adapter and this is current Section.adapter object, do nothing
            if (previousAdapter != adapter) {
                // Fully set (reset) when:
                // - first set (no adapter)
                // - new adapter hasn't same type as previous
                // - section has AdapterPolicy.RESET
                // In other cases we swap adapter to prevent visual data updates. This means we don't
                // remove views and viewHolders and try to find differences and animate them
                if (previousAdapter == null
                    || previousAdapter.javaClass != adapter.javaClass
                    || adapterPolicy == RESET
                ) {
                    sectionRV.adapter = adapter
                } else {
                    if (previousAdapter is SectionAdapter<*>) {
                        // We need to prepopulate new adapter with data from previous adapter for
                        // correct RecyclerView.swapAdapter behavior.
                        // Important: it's not tested when submitData will take a lot of time,
                        // so in these cases behavior is unexpected
                        @Suppress("UNCHECKED_CAST")
                        adapter.submitData(previousAdapter.getData() as List<T>)
                    }
                    sectionRV.swapAdapter(adapter, false)
                }
            }
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
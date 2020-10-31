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
            with(sectionRV) {
                if (adapter == null || adapterPolicy == RESET) {
                    adapter = this@Section.adapter
                } else {
                    swapAdapter(this@Section.adapter, false)
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
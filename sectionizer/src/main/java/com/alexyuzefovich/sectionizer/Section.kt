package com.alexyuzefovich.sectionizer

import androidx.annotation.IntDef
import androidx.recyclerview.widget.RecyclerView

/**
 * Section is a part of the root RecyclerView, that contains another (child) RecyclerView.
 * The main goal of this class is to control child's [RecyclerView.Adapter] as well as compare
 * with another sections and hold [DataController].
 *
 * @param adapterPolicy Whether to [RESET] or [SWAP] already set [adapter]. See [AdapterPolicy] for more details.
 *
 * @author Alexander Yuzefovich
 * */
abstract class Section<T, A>(
    @AdapterPolicy
    private val adapterPolicy: Int = SWAP
)
        where A : RecyclerView.Adapter<*>,
              A : SectionAdapter<T>
{

    companion object {
        const val RESET = 0
        const val SWAP = 1
    }

    /**
     * When section is bound in ViewHolder we need to decide what to do with adapter.
     * In case when we have already set adapter, we can try to [SWAP] it softly with a new one.
     * That should help to store all the already set data: ViewHolders and Views will leave on the screen,
     * so this change won't be visible to User. If we select [RESET] we won't try to store the data,
     * and hard update will be called.
     * */
    @IntDef(RESET, SWAP)
    @Retention(AnnotationRetention.SOURCE)
    annotation class AdapterPolicy

    /**
     * Adapter for Section's child RecyclerView.
     * */
    abstract val adapter: A

    /**
     * DataController that should trigger data loading for the this Section.
     * */
    abstract val dataController: DataController

    /**
     * Since we use [SectionsAdapter] that is subclass of [androidx.recyclerview.widget.ListAdapter]
     * and that uses [androidx.recyclerview.widget.DiffUtil], we want to call item updates only if needed.
     * So we need to compare items to decide whether to update or not.
     * */
    abstract fun isTheSameWith(another: Section<*, *>): Boolean

    /**
     * One of the methods used by [SectionsAdapter]'s [androidx.recyclerview.widget.DiffUtil].
     * See [isTheSameWith] method for more details.
     * */
    abstract fun isContentTheSameWith(another: Section<*, *>): Boolean

    /**
     * Attaches adapter to [viewHolder]'s RecyclerView based on selected [adapterPolicy].
     * */
    internal fun attachAdapter(viewHolder: SectionsAdapter.ViewHolder<*>) {
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
                        adapter.restoreFromSnapshot(previousAdapter.getLatestSnapshot() as List<T>)
                    }
                    sectionRV.swapAdapter(adapter, false)
                }
            }
        }
    }

}
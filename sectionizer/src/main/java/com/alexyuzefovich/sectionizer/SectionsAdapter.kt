package com.alexyuzefovich.sectionizer

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter to control root RecyclerView. To use this adapter you need two things:
 * - Override [onCreateViewHolder] method to create [Section]'s ViewHolder.
 * - Extend [ViewHolder] class and define there child RecyclerView and how to bind Section via [ViewHolder.bind] method.
 *
 * Adapter automatically bind [Section]s and trigger [DataController] when needed.
 *
 * Note: Since this adapter is subclass of [ListAdapter] it supports item animations by default.
 * But it is assumed that sections are often static (e.g header) and only the internal content (list) changes,
 * so animations can be ugly for sections. That's why animations are disabled by default.
 * You can enable them again by overriding the onAttachToRecyclerView method.
 *
 * In cases when you have static [Section]'s content
 * and don't expect content change behavior (only add/move/remove ops) you can make [Section.isContentTheSameWith]
 * always return true, since this method is used inside [SectionsAdapter] to determine content changes.
 *
 * @author Alexander Yuzefovich
 * */
abstract class SectionsAdapter<S : Section<*, *>, VH : SectionsAdapter.ViewHolder<S>> : ListAdapter<S, VH>(DiffUtilCallback()) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        // Disable animations to prevent glitches on Section updates
        recyclerView.itemAnimator?.let {
            if (it is DefaultItemAnimator) {
                it.supportsChangeAnimations = false
            }
        }
    }

    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindAndLoadData(getItem(position))
    }

    override fun submitList(list: List<S>?) {
        submitList(list, null)
    }

    override fun submitList(list: List<S>?, commitCallback: Runnable?) {
        submitList(list, commitCallback, false)
    }

    /**
     * Since each [Section] holds its [Section.adapter], basically we want to keep it for the same item.
     * For example, let's imagine we have a [Section] with some title (we store field for it inside).
     * We don't store Section's list data inside it, but trigger [Section.dataController] to load data
     * into the [Section.adapter]. When we update our Section list (e.g. add a new Section) we can pass
     * fully new list object to our method with Sections with empty adapters. You will see that all
     * our nested list data will disappear before the new one is loaded. To prevent this we try to
     * keep all the old items that are presented in the new [list], so Sections will stay the same
     * with the old adapters and old the data. And when data will be loaded via [Section.dataController]
     * it can be used for smooth update.
     *
     * IMPORTANT! This behavior is strongly depends on the [Section.isTheSameWith] and [Section.isContentTheSameWith]
     * methods implementation. Please see their description for correct implementation.
     *
     * @param forceUpdate Set to true if you don't want to keep old data, false otherwise.
     * */
    fun submitList(list: List<S>?, commitCallback: Runnable?, forceUpdate: Boolean) {
        val listToSubmit = if (list.isNullOrEmpty()) {
            list
        } else {
            val oldList = currentList
            val mergedList = list.map { newItem ->
                val oldItem = oldList.find { it.isTheSameWith(newItem) }
                if (oldItem != null) {
                    if (oldItem.isContentTheSameWith(newItem)) {
                        oldItem
                    } else {
                        @Suppress("UNCHECKED_CAST")
                        newItem.apply {
                            val oldAdapter = oldItem.adapter as SectionAdapter<Any>
                            val newAdapter = newItem.adapter as SectionAdapter<Any>
                            newAdapter.restoreFromSnapshot(oldAdapter.getLatestSnapshot())
                        }
                    }
                } else {
                    newItem
                }
            }

            mergedList
        }

        super.submitList(listToSubmit, commitCallback)
    }

    @CallSuper
    override fun onViewRecycled(holder: VH) {
        holder.getSectionForAdapterPosition()?.dataController?.stopDataRequests()
    }

    private fun ViewHolder<S>.getSectionForAdapterPosition(): S? =
        bindingAdapterPosition
            .takeIf { it != RecyclerView.NO_POSITION }
            ?.let { getItem(it) }


    /**
     * Simple ViewHolder to setup Section's View (e.g. define header text etc.).
     *
     * You need to define internal [sectionRV] and just setup your ViewHolder in [bind] method, that
     * called by [SectionsAdapter.onBindViewHolder] method.
     * */
    abstract class ViewHolder<S : Section<*, *>>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract val sectionRV: RecyclerView

        open fun bind(section: S) { }

        internal fun bindAndLoadData(section: S) {
            section.attachAdapter(this)
            bind(section)

            // Re-run data requests, so if User attaches callback to his DataController,
            // it will be triggered.
            with(section.dataController) {
                stopDataRequests()
                startDataRequests()
            }
        }

    }

    private class DiffUtilCallback<S : Section<*, *>> : DiffUtil.ItemCallback<S>() {
        // Formally used for adding/moving/removing new items
        override fun areItemsTheSame(oldItem: S, newItem: S): Boolean =
            oldItem.isTheSameWith(newItem)

        // Used for all other section data updates including list
        override fun areContentsTheSame(oldItem: S, newItem: S): Boolean =
            oldItem.isContentTheSameWith(newItem)
    }

}
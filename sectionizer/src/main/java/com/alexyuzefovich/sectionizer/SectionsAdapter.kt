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
 * @param areSectionsStatic Indicate whether section content is always the same.
 * This can be useful when the list content is static and does not need to be updated (e.g., a local json file).
 * In such cases, specify true, and the sections will be compared by the selected identifier in the [Section.isTheSameWith] method.
 * Otherwise, if the data is dynamic, specify false (default value).
 *
 * @author Alexander Yuzefovich
 * */
abstract class SectionsAdapter<S : Section<*, *>, VH : SectionsAdapter.ViewHolder<S>>(
    areSectionsStatic: Boolean = false
) : ListAdapter<S, VH>(DiffUtilCallback(areSectionsStatic)) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        // Disable animations to prevent glitches on Section updates
        recyclerView.itemAnimator?.let {
            if (it is DefaultItemAnimator) {
                it.supportsChangeAnimations = false
            }
        }
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindAndLoadData(getItem(position))
    }

    @CallSuper
    override fun onViewAttachedToWindow(holder: VH) {
        holder.getSectionForAdapterPosition()?.dataController?.startDataRequests()
    }

    @CallSuper
    override fun onViewDetachedFromWindow(holder: VH) {
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
        }

    }

    private class DiffUtilCallback<S : Section<*, *>>(
        private val areSectionsStatic: Boolean
    ) : DiffUtil.ItemCallback<S>() {
        // Formally used for adding/removing new items
        override fun areItemsTheSame(oldItem: S, newItem: S): Boolean =
            oldItem.isTheSameWith(newItem)

        // Used for all other section data updates including list
        override fun areContentsTheSame(oldItem: S, newItem: S): Boolean =
            oldItem.isContentTheSameWith(newItem) || areSectionsStatic
    }

}
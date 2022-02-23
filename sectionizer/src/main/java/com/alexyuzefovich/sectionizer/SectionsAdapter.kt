package com.alexyuzefovich.sectionizer

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class SectionsAdapter<S : Section<*, *>, VH : SectionsAdapter.ViewHolder<S>>(
    areSectionsStatic: Boolean = false
) : ListAdapter<S, VH>(DiffUtilCallback(areSectionsStatic)) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
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
        getItem(bindingAdapterPosition)


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
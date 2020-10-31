package com.alexyuzefovich.sectionizer

import android.view.View
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


    abstract class ViewHolder<S : Section<*, *>>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract val sectionRV: RecyclerView

        var loadCallback: Section.LoadCallback? = null

        open fun bind(section: S) { }

        internal fun bindAndLoadData(section: S) {
            bind(section)
            section.loadInto(this)
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
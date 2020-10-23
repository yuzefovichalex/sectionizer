package com.alexyuzefovich.sectionizer

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class SectionsAdapter<S : Section<*, *>, VH : SectionsAdapter.ViewHolder<S>> :
    ListAdapter<S, VH>(DiffUtilCallback())
{

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

    private class DiffUtilCallback<S : Section<*, *>> : DiffUtil.ItemCallback<S>() {
        override fun areItemsTheSame(oldItem: S, newItem: S): Boolean =
            oldItem.isTheSameWith(newItem)

        override fun areContentsTheSame(oldItem: S, newItem: S): Boolean = true
    }

}
package com.alexyuzefovich.sectionizer

import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class SectionsAdapter<VH : SectionsAdapter.ViewHolder> :
    ListAdapter<Section<*, *>, VH>(Section.DiffUtilCallback)
{

    final override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindAndLoadData(getItem(position))
    }


    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract val sectionRV: RecyclerView

        open fun bind(section: Section<*, *>) { }

        internal fun bindAndLoadData(section: Section<*, *>) {
            bind(section)
            section.loadInto(sectionRV)
        }

    }

}
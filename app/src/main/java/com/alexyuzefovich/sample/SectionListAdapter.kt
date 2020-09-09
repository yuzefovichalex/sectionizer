package com.alexyuzefovich.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.databinding.ItemSectionBinding
import com.alexyuzefovich.sectionizer.Section
import com.alexyuzefovich.sectionizer.SectionAdapter

class SectionListAdapter :
    ListAdapter<Section<*, *>, SectionListAdapter.ViewHolder>(Section.DiffUtilCallback),
    SectionAdapter<Section<*, *>>
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSectionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitData(items: List<Section<*, *>>) {
        submitList(items)
    }


    class ViewHolder(
        private val binding: ItemSectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(section: Section<*, *>) {
            section.loadInto(binding.itemList)
        }

    }

}
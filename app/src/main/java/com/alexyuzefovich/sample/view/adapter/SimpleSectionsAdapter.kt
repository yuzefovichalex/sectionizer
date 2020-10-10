package com.alexyuzefovich.sample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.databinding.ItemSectionBinding
import com.alexyuzefovich.sample.model.SimpleSection
import com.alexyuzefovich.sample.util.HorizontalSpaceItemDecoration
import com.alexyuzefovich.sample.util.dp
import com.alexyuzefovich.sectionizer.Section
import com.alexyuzefovich.sectionizer.SectionsAdapter

class SimpleSectionsAdapter : SectionsAdapter<SimpleSectionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSectionBinding.inflate(inflater, parent, false).apply {
            itemList.addItemDecoration(
                HorizontalSpaceItemDecoration(8.dp)
            )
        }
        return ViewHolder(binding)
    }


    class ViewHolder(
        private val binding: ItemSectionBinding
    ) : SectionsAdapter.ViewHolder(binding.root) {

        override val sectionRV: RecyclerView
            get() = binding.itemList

        override fun bind(section: Section<*, *>) {
            if (section is SimpleSection) {
                with(binding) {
                    name.text = section.name
                }
            }
        }

    }

}
package com.alexyuzefovich.sample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.databinding.ItemHorizontalSectionBinding
import com.alexyuzefovich.sample.databinding.ItemVerticalSectionBinding
import com.alexyuzefovich.sample.model.SimpleSection
import com.alexyuzefovich.sample.util.HorizontalSpaceItemDecoration
import com.alexyuzefovich.sample.util.dp
import com.alexyuzefovich.sectionizer.Section
import com.alexyuzefovich.sectionizer.SectionsAdapter
import java.lang.IllegalArgumentException

class SimpleSectionsAdapter : SectionsAdapter<SectionsAdapter.ViewHolder>() {

    companion object {
        private const val VERTICAL_SECTION = 0
        private const val HORIZONTAL_SECTION = 1
    }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> VERTICAL_SECTION
            else -> HORIZONTAL_SECTION
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VERTICAL_SECTION -> {
                val binding = ItemVerticalSectionBinding.inflate(inflater, parent, false).apply {
                    itemList.addItemDecoration(
                        HorizontalSpaceItemDecoration(8.dp)
                    )
                }
                VerticalViewHolder(binding)
            }
            HORIZONTAL_SECTION -> {
                val binding = ItemHorizontalSectionBinding.inflate(inflater, parent, false).apply {
                    itemList.addItemDecoration(
                        HorizontalSpaceItemDecoration(8.dp)
                    )
                }
                HorizontalViewHolder(binding)
            }
            else -> throw IllegalArgumentException("viewType must be VERTICAL_SECTION or HORIZONTAL_SECTION")
        }
    }

    class VerticalViewHolder(
        private val binding: ItemVerticalSectionBinding
    ) : ViewHolder(binding.root) {

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

    class HorizontalViewHolder(
        private val binding: ItemHorizontalSectionBinding
    ) : ViewHolder(binding.root) {

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
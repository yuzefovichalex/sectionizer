package com.alexyuzefovich.sample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.databinding.ItemHorizontalSectionBinding
import com.alexyuzefovich.sample.databinding.ItemVerticalSectionBinding
import com.alexyuzefovich.sample.model.HorizontalSection
import com.alexyuzefovich.sample.model.VerticalSection
import com.alexyuzefovich.sample.util.SpaceItemDecoration
import com.alexyuzefovich.sample.util.dp
import com.alexyuzefovich.sectionizer.Section
import com.alexyuzefovich.sectionizer.SectionsAdapter
import java.lang.IllegalArgumentException

class MultipleTypeSectionsAdapter :
    SectionsAdapter<Section<*, *>, SectionsAdapter.ViewHolder<Section<*, *>>>()
{

    companion object {
        private const val VERTICAL_SECTION = 0
        private const val HORIZONTAL_SECTION = 1
    }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            1 -> VERTICAL_SECTION
            else -> HORIZONTAL_SECTION
        }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Section<*, *>> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VERTICAL_SECTION -> {
                val binding = ItemVerticalSectionBinding.inflate(inflater, parent, false).apply {
                    itemList.addItemDecoration(
                        SpaceItemDecoration(24.dp, RecyclerView.VERTICAL)
                    )
                }
                VerticalViewHolder(binding) as ViewHolder<Section<*, *>>
            }
            HORIZONTAL_SECTION -> {
                val binding = ItemHorizontalSectionBinding.inflate(inflater, parent, false).apply {
                    itemList.addItemDecoration(
                        SpaceItemDecoration(16.dp, RecyclerView.HORIZONTAL)
                    )
                }
                HorizontalViewHolder(binding) as ViewHolder<Section<*, *>>
            }
            else -> throw IllegalArgumentException("viewType must be VERTICAL_SECTION or HORIZONTAL_SECTION")
        }
    }

    class VerticalViewHolder(
        private val binding: ItemVerticalSectionBinding
    ) : ViewHolder<VerticalSection<*, *>>(binding.root) {

        override val sectionRV: RecyclerView
            get() = binding.itemList


        init {
            loadCallback = object : Section.LoadCallback {
                override fun onLoadStart() { }

                override fun onLoadSuccess() { }

                override fun onLoadError(throwable: Throwable?) {
                    Toast.makeText(binding.root.context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }


        override fun bind(section: VerticalSection<*, *>) {
            with(binding) {
                name.text = section.name
            }
        }

    }

    class HorizontalViewHolder(
        private val binding: ItemHorizontalSectionBinding
    ) : ViewHolder<HorizontalSection<*, *>>(binding.root) {

        override val sectionRV: RecyclerView
            get() = binding.itemList

        override fun bind(section: HorizontalSection<*, *>) {
            with(binding) {
                name.text = section.name
            }
        }

    }

}
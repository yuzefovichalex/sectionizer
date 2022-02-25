package com.alexyuzefovich.sample.presentation.view.adapter.sections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.databinding.ItemHorizontalSectionBinding
import com.alexyuzefovich.sample.presentation.view.model.HorizontalSection
import com.alexyuzefovich.sample.util.SpaceItemDecoration
import com.alexyuzefovich.sample.util.dp
import com.alexyuzefovich.sectionizer.SectionsAdapter

class SingleTypeSectionsAdapter :
    SectionsAdapter<HorizontalSection<*, *>, SingleTypeSectionsAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHorizontalSectionBinding.inflate(inflater, parent, false).apply {
            itemList.addItemDecoration(
                SpaceItemDecoration(8.dp, RecyclerView.HORIZONTAL)
            )
        }
        return ViewHolder(binding)
    }

    class ViewHolder(
        private val binding: ItemHorizontalSectionBinding
    ) : SectionsAdapter.ViewHolder<HorizontalSection<*, *>>(binding.root) {

        override val sectionRV: RecyclerView
            get() = binding.itemList

        override fun bind(section: HorizontalSection<*, *>) {
            with(binding) {
                name.text = section.name
            }
        }

    }

}
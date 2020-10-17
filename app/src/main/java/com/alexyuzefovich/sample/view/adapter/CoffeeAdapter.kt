package com.alexyuzefovich.sample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.databinding.ItemCoffeeBinding
import com.alexyuzefovich.sample.model.Coffee
import com.alexyuzefovich.sample.util.glide
import com.alexyuzefovich.sectionizer.SectionAdapter

class CoffeeAdapter :
    ListAdapter<Coffee, CoffeeAdapter.ViewHolder>(DiffUtilCallback),
    SectionAdapter<Coffee>
{

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Coffee>() {
        override fun areItemsTheSame(
            oldItem: Coffee,
            newItem: Coffee
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Coffee,
            newItem: Coffee
        ): Boolean = oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCoffeeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitData(items: List<Coffee>) {
        submitList(items)
    }

    class ViewHolder(
        private val binding: ItemCoffeeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coffee: Coffee) {
            with(binding) {
                image.glide(coffee.imageUrl)
                name.text = coffee.name
            }
        }

    }

}
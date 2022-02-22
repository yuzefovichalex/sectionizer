package com.alexyuzefovich.sample.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.R
import com.alexyuzefovich.sample.data.DataListener
import com.alexyuzefovich.sample.databinding.ItemCoffeeBinding
import com.alexyuzefovich.sample.model.Coffee
import com.alexyuzefovich.sample.util.glide
import com.alexyuzefovich.sectionizer.SectionAdapter

class CoffeeAdapter :
    ListAdapter<Coffee, CoffeeAdapter.ViewHolder>(DiffUtilCallback),
    SectionAdapter<Coffee>,
    DataListener<Coffee>
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

    override fun getLatestSnapshot(): List<Coffee> = currentList

    override fun restoreFromSnapshot(snapshot: List<Coffee>) {
        submitList(snapshot)
    }

    override fun onDataReady(data: List<Coffee>) {
        submitList(data)
    }

    class ViewHolder(
        private val binding: ItemCoffeeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context: Context
            get() = binding.root.context

        fun bind(coffee: Coffee) {
            with(binding) {
                image.glide(coffee.imageUrl)
                name.text = coffee.name
                cost.text = context.getString(R.string.cost, coffee.cost)
            }
        }

    }

}
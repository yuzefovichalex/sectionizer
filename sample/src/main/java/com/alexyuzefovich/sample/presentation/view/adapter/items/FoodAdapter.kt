package com.alexyuzefovich.sample.presentation.view.adapter.items

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.R
import com.alexyuzefovich.sample.data.DataListener
import com.alexyuzefovich.sample.databinding.ItemFoodBinding
import com.alexyuzefovich.sample.data.model.Food
import com.alexyuzefovich.sample.util.glide
import com.alexyuzefovich.sectionizer.SectionAdapter

class FoodAdapter(
    private val itemClickListener: (Food) -> Unit
) :
    ListAdapter<Food, FoodAdapter.ViewHolder>(DiffUtilCallback),
    SectionAdapter<Food>,
    DataListener<Food>
{

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(
            oldItem: Food,
            newItem: Food
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Food,
            newItem: Food
        ): Boolean = oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodBinding.inflate(inflater, parent, false)
        val viewHolder = ViewHolder(binding)
        binding.apply {
            root.setOnClickListener {
                val clickedItem = getItem(viewHolder.adapterPosition)
                itemClickListener(clickedItem)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getLatestSnapshot(): List<Food> = currentList

    override fun restoreFromSnapshot(snapshot: List<Food>) {
        submitList(snapshot)
    }

    override fun onDataReady(data: List<Food>) {
        submitList(data)
    }

    class ViewHolder(
        private val binding: ItemFoodBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context: Context
            get() = binding.root.context

        fun bind(food: Food) {
            with(binding) {
                cover.glide(food.imageUrl)
                name.text = food.name
                description.text = food.description
                rate.text = "${food.rate}"
                reviewCount.text = context.getString(R.string.ratings, food.votes)
            }
        }

    }

}
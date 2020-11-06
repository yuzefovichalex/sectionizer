package com.alexyuzefovich.sample.view.adapter

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.databinding.ItemFoodCategoryBinding
import com.alexyuzefovich.sample.model.FoodCategory
import com.alexyuzefovich.sample.util.glide
import com.alexyuzefovich.sectionizer.SectionAdapter

class FoodCategoryAdapter :
    ListAdapter<FoodCategory, FoodCategoryAdapter.ViewHolder>(DiffUtilCallback),
    SectionAdapter<FoodCategory>
{

    companion object DiffUtilCallback : DiffUtil.ItemCallback<FoodCategory>() {
        override fun areItemsTheSame(
            oldItem: FoodCategory,
            newItem: FoodCategory
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: FoodCategory,
            newItem: FoodCategory
        ): Boolean = oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getData(): List<FoodCategory> = currentList

    override fun submitData(items: List<FoodCategory>) {
        submitList(items)
    }

    class ViewHolder(
        private val binding: ItemFoodCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(foodCategory: FoodCategory) {
            with(binding) {
                val iconUri = Uri.parse(foodCategory.icon)
                val primaryColor = Color.parseColor(foodCategory.color)
                val secondaryColor = ColorUtils.blendARGB(primaryColor, Color.WHITE, 0.75f)

                container.setCardBackgroundColor(secondaryColor)
                icon.apply {
                    glide(iconUri)
                    setColorFilter(primaryColor)
                }
                name.apply {
                    text = foodCategory.name
                    setTextColor(primaryColor)
                }
            }
        }

    }

}
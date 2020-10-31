package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.view.adapter.FoodAdapter
import com.alexyuzefovich.sectionizer.SectionDataLoader

class TopFoodSection(
    override val name: String,
    override val sectionDataLoader: SectionDataLoader<Food, *>,
    itemClickListener: (Food) -> Unit
) : VerticalSection<Food, FoodAdapter>() {

    override val adapter: FoodAdapter = FoodAdapter(itemClickListener)

}
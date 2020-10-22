package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.view.adapter.FoodAdapter
import com.alexyuzefovich.sectionizer.SectionDataLoader

class TopFoodSection(
    override val name: String,
    override val adapter: FoodAdapter,
    override val sectionDataLoader: SectionDataLoader<Food, *>
) : VerticalSection<Food, FoodAdapter>()
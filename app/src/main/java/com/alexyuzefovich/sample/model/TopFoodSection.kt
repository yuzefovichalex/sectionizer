package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.view.adapter.FoodAdapter
import com.alexyuzefovich.sectionizer.LoaderDelegate

class TopFoodSection(
    override val name: String,
    override val adapter: FoodAdapter,
    override val loaderDelegate: LoaderDelegate<Food, *>
) : SimpleSection<Food, FoodAdapter>()
package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.view.adapter.FoodCategoryAdapter
import com.alexyuzefovich.sectionizer.LoaderDelegate

class FoodCategorySection(
    override val name: String,
    override val adapter: FoodCategoryAdapter,
    override val loaderDelegate: LoaderDelegate<FoodCategory, *>
) : HorizontalSection<FoodCategory, FoodCategoryAdapter>()
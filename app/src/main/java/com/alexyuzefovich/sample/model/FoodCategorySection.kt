package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.view.adapter.FoodCategoryAdapter
import com.alexyuzefovich.sectionizer.LoaderDelegate
import com.alexyuzefovich.sectionizer.Section

class FoodCategorySection(
    override val adapter: FoodCategoryAdapter,
    override val loaderDelegate: LoaderDelegate<FoodCategory, *>
) : Section<FoodCategory, FoodCategoryAdapter>() {

    override fun isTheSameWith(another: Section<*, *>): Boolean = false

}
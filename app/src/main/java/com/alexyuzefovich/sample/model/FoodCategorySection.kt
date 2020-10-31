package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.view.adapter.FoodCategoryAdapter
import com.alexyuzefovich.sectionizer.SectionDataLoader

class FoodCategorySection(
    override val name: String,
    override val sectionDataLoader: SectionDataLoader<FoodCategory, *>
) : HorizontalSection<FoodCategory, FoodCategoryAdapter>() {

    override val adapter: FoodCategoryAdapter = FoodCategoryAdapter()

}
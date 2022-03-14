package com.alexyuzefovich.sample.presentation.view.model

import com.alexyuzefovich.sample.data.DataExecutor
import com.alexyuzefovich.sample.data.model.FoodCategory
import com.alexyuzefovich.sample.presentation.view.adapter.items.FoodCategoryAdapter
import com.alexyuzefovich.sectionizer.DataController

class FoodCategorySection(
    override val name: String,
    override val adapter: FoodCategoryAdapter,
    private val dataExecutor: DataExecutor<FoodCategory>
) : HorizontalSection<FoodCategory, FoodCategoryAdapter>() {

    override val dataController: DataController = object : DataController {
        override fun startDataRequests() {
            dataExecutor.execute()
        }

        override fun stopDataRequests() { }
    }

}
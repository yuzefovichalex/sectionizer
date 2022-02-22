package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.data.DataExecutor
import com.alexyuzefovich.sample.view.adapter.FoodCategoryAdapter
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
package com.alexyuzefovich.sample.presentation.view.model

import com.alexyuzefovich.sample.data.DataExecutor
import com.alexyuzefovich.sample.data.model.Food
import com.alexyuzefovich.sample.presentation.view.adapter.items.FoodAdapter
import com.alexyuzefovich.sectionizer.DataController

class TopFoodSection(
    override val name: String,
    override val adapter: FoodAdapter,
    private val dataExecutor: DataExecutor<Food>
) : VerticalSection<Food, FoodAdapter>() {

    override val dataController: DataController = object : DataController {
        override fun startDataRequests() {
            dataExecutor.execute()
        }

        override fun stopDataRequests() {

        }
    }

}
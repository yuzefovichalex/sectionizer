package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.data.DataExecutor
import com.alexyuzefovich.sample.view.adapter.FoodAdapter
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
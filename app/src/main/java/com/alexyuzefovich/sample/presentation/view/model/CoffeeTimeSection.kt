package com.alexyuzefovich.sample.presentation.view.model

import com.alexyuzefovich.sample.data.DataExecutor
import com.alexyuzefovich.sample.data.model.Coffee
import com.alexyuzefovich.sample.presentation.view.adapter.items.CoffeeAdapter
import com.alexyuzefovich.sectionizer.DataController

class CoffeeTimeSection(
    override val name: String,
    override val adapter: CoffeeAdapter,
    private val dataExecutor: DataExecutor<Coffee>
) : HorizontalSection<Coffee, CoffeeAdapter>() {

    override val dataController: DataController = object : DataController {
        override fun startDataRequests() {
            dataExecutor.execute()
        }

        override fun stopDataRequests() { }
    }

}
package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.data.DataExecutor
import com.alexyuzefovich.sample.view.adapter.CoffeeAdapter
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
package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.view.adapter.CoffeeAdapter
import com.alexyuzefovich.sectionizer.LoaderDelegate
import com.alexyuzefovich.sectionizer.Section

class CoffeeTimeSection(
    override val name: String,
    override val adapter: CoffeeAdapter,
    override val loaderDelegate: LoaderDelegate<Coffee, *>
) : SimpleSection<Coffee, CoffeeAdapter>() {

    override fun isTheSameWith(another: Section<*, *>): Boolean = false

}
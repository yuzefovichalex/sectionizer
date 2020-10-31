package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.view.adapter.CoffeeAdapter
import com.alexyuzefovich.sectionizer.SectionDataLoader

class CoffeeTimeSection(
    override val name: String,
    override val sectionDataLoader: SectionDataLoader<Coffee, *>
) : HorizontalSection<Coffee, CoffeeAdapter>() {

    override val adapter: CoffeeAdapter = CoffeeAdapter()

}
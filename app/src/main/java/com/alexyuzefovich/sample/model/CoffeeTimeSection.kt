package com.alexyuzefovich.sample.model

import com.alexyuzefovich.sample.view.adapter.CoffeeAdapter
import com.alexyuzefovich.sectionizer.LoaderDelegate

class CoffeeTimeSection(
    override val name: String,
    override val adapter: CoffeeAdapter,
    override val loaderDelegate: LoaderDelegate<Coffee, *>
) : HorizontalSection<Coffee, CoffeeAdapter>()
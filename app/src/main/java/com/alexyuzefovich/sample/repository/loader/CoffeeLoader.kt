package com.alexyuzefovich.sample.repository.loader

import com.alexyuzefovich.sample.model.Coffee
import com.alexyuzefovich.sample.repository.SampleRepository
import com.alexyuzefovich.sectionizer.LoadParams
import com.alexyuzefovich.sectionizer.LoadResult
import com.alexyuzefovich.sectionizer.SectionDataLoader
import kotlinx.coroutines.CoroutineScope

class CoffeeLoader(
    private val repository: SampleRepository,
    coroutineScope: CoroutineScope
) : SectionDataLoader<Coffee, LoadParams>(coroutineScope) {

    override val params: LoadParams = LoadParams.EMPTY

    override fun loadData(): LoadResult<Coffee> {
        val coffee = repository.loadCoffee()
        return LoadResult(coffee)
    }

}
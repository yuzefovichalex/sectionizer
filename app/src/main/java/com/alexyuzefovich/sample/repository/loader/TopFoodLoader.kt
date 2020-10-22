package com.alexyuzefovich.sample.repository.loader

import com.alexyuzefovich.sample.model.Food
import com.alexyuzefovich.sample.repository.SampleRepository
import com.alexyuzefovich.sectionizer.LoadParams
import com.alexyuzefovich.sectionizer.LoadResult
import com.alexyuzefovich.sectionizer.SectionDataLoader
import kotlinx.coroutines.CoroutineScope

class TopFoodLoader(
    private val repository: SampleRepository,
    coroutineScope: CoroutineScope
) : SectionDataLoader<Food, LoadParams>(coroutineScope) {

    override val params: LoadParams = LoadParams.EMPTY

    override fun loadData(): LoadResult<Food> {
        val topFood = repository.loadTopFood()
        return LoadResult(topFood)
    }

}
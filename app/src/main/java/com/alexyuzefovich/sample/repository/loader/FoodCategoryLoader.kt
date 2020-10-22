package com.alexyuzefovich.sample.repository.loader

import com.alexyuzefovich.sample.model.FoodCategory
import com.alexyuzefovich.sample.repository.SampleRepository
import com.alexyuzefovich.sectionizer.LoadParams
import com.alexyuzefovich.sectionizer.LoadResult
import com.alexyuzefovich.sectionizer.LoaderDelegate
import kotlinx.coroutines.CoroutineScope

class FoodCategoryLoader(
    private val repository: SampleRepository,
    coroutineScope: CoroutineScope
) : LoaderDelegate<FoodCategory, LoadParams>(coroutineScope) {

    override val params: LoadParams = LoadParams.EMPTY

    override fun loadData(): LoadResult<FoodCategory> {
        val foodCategories = repository.loadFoodCategories()
        return LoadResult(foodCategories)
    }

}
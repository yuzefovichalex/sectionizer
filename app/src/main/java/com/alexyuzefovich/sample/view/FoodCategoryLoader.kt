package com.alexyuzefovich.sample.view

import android.content.Context
import com.alexyuzefovich.sample.model.FoodCategory
import com.alexyuzefovich.sample.util.fromJson
import com.alexyuzefovich.sectionizer.LoadParams
import com.alexyuzefovich.sectionizer.LoadResult
import com.alexyuzefovich.sectionizer.LoaderDelegate
import kotlinx.coroutines.CoroutineScope

class FoodCategoryLoader(
    private val context: Context,
    coroutineScope: CoroutineScope
) : LoaderDelegate<FoodCategory, LoadParams>(coroutineScope) {

    override val params: LoadParams = LoadParams.EMPTY

    override fun loadData(): LoadResult<FoodCategory> {
        val foodCategories = context.assets.open("food_category_source.json")
            .bufferedReader()
            .fromJson<List<FoodCategory>>()
        return LoadResult(foodCategories)
    }

}
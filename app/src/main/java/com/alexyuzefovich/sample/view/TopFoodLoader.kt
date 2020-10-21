package com.alexyuzefovich.sample.view

import android.content.Context
import com.alexyuzefovich.sample.model.Food
import com.alexyuzefovich.sample.util.fromJson
import com.alexyuzefovich.sectionizer.LoadParams
import com.alexyuzefovich.sectionizer.LoadResult
import com.alexyuzefovich.sectionizer.LoaderDelegate

class TopFoodLoader(
    private val context: Context
) : LoaderDelegate<Food, LoadParams>() {

    override val params: LoadParams = LoadParams.EMPTY

    override fun loadData(): LoadResult<Food> {
        val topFood = context.assets.open("top_food_source.json")
            .bufferedReader()
            .fromJson<List<Food>>()
        return LoadResult(topFood)
    }

}
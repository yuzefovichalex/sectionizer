package com.alexyuzefovich.sample.view

import android.content.Context
import com.alexyuzefovich.sample.model.Coffee
import com.alexyuzefovich.sample.util.fromJson
import com.alexyuzefovich.sectionizer.LoadParams
import com.alexyuzefovich.sectionizer.LoadResult
import com.alexyuzefovich.sectionizer.LoaderDelegate

class CoffeeLoader(
    private val context: Context
) : LoaderDelegate<Coffee, LoadParams>() {

    override val params: LoadParams = LoadParams.EMPTY

    override fun loadData(): LoadResult<Coffee> {
        val coffee = context.assets.open("coffee_time_source.json")
            .bufferedReader()
            .fromJson<List<Coffee>>()
        return LoadResult(coffee)
    }

}
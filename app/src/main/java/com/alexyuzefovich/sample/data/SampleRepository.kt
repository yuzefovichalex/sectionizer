package com.alexyuzefovich.sample.data

import android.content.Context
import com.alexyuzefovich.sample.model.Coffee
import com.alexyuzefovich.sample.model.Food
import com.alexyuzefovich.sample.model.FoodCategory
import com.alexyuzefovich.sample.util.fromJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SampleRepository(
    private val context: Context
) {

    suspend fun loadTopFood(): List<Food> = load("top_food_source.json")

    suspend fun loadCoffee(): List<Coffee> = load("coffee_time_source.json")

    suspend fun loadFoodCategories(): List<FoodCategory> = load("food_category_source.json")

    private suspend inline fun <reified T> load(fileName: String): T =
        withContext(Dispatchers.IO) {
            context.assets.open(fileName)
                .bufferedReader()
                .fromJson()
        }

}
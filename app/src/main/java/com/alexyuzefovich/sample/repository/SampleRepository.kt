package com.alexyuzefovich.sample.repository

import android.content.Context
import com.alexyuzefovich.sample.model.Coffee
import com.alexyuzefovich.sample.model.Food
import com.alexyuzefovich.sample.model.FoodCategory
import com.alexyuzefovich.sample.util.fromJson

class SampleRepository(
    private val context: Context
) {

    fun loadTopFood(): List<Food> =
        context.assets.open("top_food_source.json")
            .bufferedReader()
            .fromJson()

    fun loadCoffee(): List<Coffee> =
        context.assets.open("coffee_time_source.json")
            .bufferedReader()
            .fromJson()

    fun loadFoodCategories(): List<FoodCategory> =
        context.assets.open("food_category_source.json")
            .bufferedReader()
            .fromJson()

}
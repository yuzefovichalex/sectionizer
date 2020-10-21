package com.alexyuzefovich.sample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexyuzefovich.sample.R
import com.alexyuzefovich.sample.model.CoffeeTimeSection
import com.alexyuzefovich.sample.model.FoodCategorySection
import com.alexyuzefovich.sample.model.TopFoodSection
import com.alexyuzefovich.sample.view.CoffeeLoader
import com.alexyuzefovich.sample.view.FoodCategoryLoader
import com.alexyuzefovich.sample.view.TopFoodLoader
import com.alexyuzefovich.sample.view.adapter.CoffeeAdapter
import com.alexyuzefovich.sample.view.adapter.FoodAdapter
import com.alexyuzefovich.sample.view.adapter.FoodCategoryAdapter
import com.alexyuzefovich.sectionizer.Section

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val topFoodSection: TopFoodSection by lazy {
        TopFoodSection(
            application.getString(R.string.on_a_pedestal),
            FoodAdapter(),
            TopFoodLoader(application, viewModelScope)
        )
    }

    private val coffeeSection: CoffeeTimeSection by lazy {
        CoffeeTimeSection(
            application.getString(R.string.coffee_time),
            CoffeeAdapter(),
            CoffeeLoader(application, viewModelScope)
        )
    }

    private val foodCategorySection: FoodCategorySection by lazy {
        FoodCategorySection(
            application.getString(R.string.food_categories),
            FoodCategoryAdapter(),
            FoodCategoryLoader(application, viewModelScope)
        )
    }

    val sections: List<Section<*, *>> = listOf(topFoodSection, coffeeSection, foodCategorySection)

}
package com.alexyuzefovich.sample.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alexyuzefovich.sample.data.DataExecutor
import com.alexyuzefovich.sample.data.DataListener
import com.alexyuzefovich.sample.data.SampleRepository
import com.alexyuzefovich.sample.data.model.Coffee
import com.alexyuzefovich.sample.data.model.Food
import com.alexyuzefovich.sample.data.model.FoodCategory

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SampleRepository = SampleRepository(application)

    fun createTopFoodDataExecutor(dataListener: DataListener<Food>): DataExecutor<Food> =
        DataExecutor(dataListener, viewModelScope) {
            repository.loadTopFood()
        }

    fun createFoodCategoryDataExecutor(dataListener: DataListener<FoodCategory>): DataExecutor<FoodCategory> =
        DataExecutor(dataListener, viewModelScope) {
            repository.loadFoodCategories()
        }

    fun createCoffeeTimeDataExecutor(dataListener: DataListener<Coffee>): DataExecutor<Coffee> =
        DataExecutor(dataListener, viewModelScope) {
            repository.loadCoffee()
        }

}
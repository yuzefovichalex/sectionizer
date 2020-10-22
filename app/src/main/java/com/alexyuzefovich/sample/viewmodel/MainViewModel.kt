package com.alexyuzefovich.sample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alexyuzefovich.sample.repository.SampleRepository
import com.alexyuzefovich.sample.repository.loader.CoffeeLoader
import com.alexyuzefovich.sample.repository.loader.FoodCategoryLoader
import com.alexyuzefovich.sample.repository.loader.TopFoodLoader

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SampleRepository = SampleRepository(application)

    val topFoodLoader: TopFoodLoader = TopFoodLoader(repository, viewModelScope)

    val coffeeLoader: CoffeeLoader = CoffeeLoader(repository, viewModelScope)

    val foodCategoryLoader: FoodCategoryLoader = FoodCategoryLoader(repository, viewModelScope)

}
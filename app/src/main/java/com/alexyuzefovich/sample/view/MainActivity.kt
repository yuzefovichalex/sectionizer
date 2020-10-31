package com.alexyuzefovich.sample.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alexyuzefovich.sample.R
import com.alexyuzefovich.sample.databinding.ActivityMainBinding
import com.alexyuzefovich.sample.model.CoffeeTimeSection
import com.alexyuzefovich.sample.model.FoodCategorySection
import com.alexyuzefovich.sample.model.TopFoodSection
import com.alexyuzefovich.sample.view.adapter.MultipleTypeSectionsAdapter
import com.alexyuzefovich.sample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initSections()
    }

    private fun initSections() {
        val sections = listOf(
            TopFoodSection(
                getString(R.string.on_a_pedestal),
                mainViewModel.topFoodLoader
            ) { },
            CoffeeTimeSection(
                getString(R.string.coffee_time),
                mainViewModel.coffeeLoader
            ),
            FoodCategorySection(
                getString(R.string.food_categories),
                mainViewModel.foodCategoryLoader
            )
        )
        binding.sections.adapter = MultipleTypeSectionsAdapter().apply {
            submitList(sections)
        }
    }

}
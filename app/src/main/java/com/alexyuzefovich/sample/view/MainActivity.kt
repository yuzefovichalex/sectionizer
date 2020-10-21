package com.alexyuzefovich.sample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexyuzefovich.sample.R
import com.alexyuzefovich.sample.databinding.ActivityMainBinding
import com.alexyuzefovich.sample.model.CoffeeTimeSection
import com.alexyuzefovich.sample.model.FoodCategorySection
import com.alexyuzefovich.sample.model.TopFoodSection
import com.alexyuzefovich.sample.view.adapter.CoffeeAdapter
import com.alexyuzefovich.sample.view.adapter.FoodAdapter
import com.alexyuzefovich.sample.view.adapter.FoodCategoryAdapter
import com.alexyuzefovich.sample.view.adapter.SimpleSectionsAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
        val sections = listOf(topFoodSection, coffeeSection, foodCategorySection)
        binding.sections.adapter = SimpleSectionsAdapter().apply {
            submitList(sections)
        }
    }

    val topFoodSection: TopFoodSection by lazy {
        TopFoodSection(
            getString(R.string.on_a_pedestal),
            FoodAdapter(),
            TopFoodLoader(this)
        )
    }

    val coffeeSection: CoffeeTimeSection by lazy {
        CoffeeTimeSection(
            getString(R.string.coffee_time),
            CoffeeAdapter(),
            CoffeeLoader(this)
        )
    }

    val foodCategorySection: FoodCategorySection by lazy {
        FoodCategorySection(
            getString(R.string.food_categories),
            FoodCategoryAdapter(),
            FoodCategoryLoader(this)
        )
    }

}
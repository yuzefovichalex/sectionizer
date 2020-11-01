package com.alexyuzefovich.sample.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.R
import com.alexyuzefovich.sample.databinding.ActivityMainBinding
import com.alexyuzefovich.sample.model.CoffeeTimeSection
import com.alexyuzefovich.sample.model.FoodCategorySection
import com.alexyuzefovich.sample.model.TopFoodSection
import com.alexyuzefovich.sample.util.SpaceItemDecoration
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
            FoodCategorySection(
                getString(R.string.food_categories_title),
                mainViewModel.foodCategoryLoader
            ),
            TopFoodSection(
                getString(R.string.top_food_title),
                mainViewModel.topFoodLoader
            ) { },
            CoffeeTimeSection(
                getString(R.string.coffee_time),
                mainViewModel.coffeeLoader
            )
        )
        with(binding.sections) {
            adapter = MultipleTypeSectionsAdapter().apply {
                submitList(sections)
            }
            addItemDecoration(
                SpaceItemDecoration(
                    resources.getDimensionPixelOffset(R.dimen.space_2x),
                    RecyclerView.VERTICAL
                )
            )
        }
    }

}
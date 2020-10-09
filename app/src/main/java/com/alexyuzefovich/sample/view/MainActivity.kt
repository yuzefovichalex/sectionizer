package com.alexyuzefovich.sample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexyuzefovich.sample.R
import com.alexyuzefovich.sample.databinding.ActivityMainBinding
import com.alexyuzefovich.sample.model.FoodCategorySection
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
        val sections = listOf(foodCategorySection)
        binding.sections.adapter = SimpleSectionsAdapter().apply {
            submitList(sections)
        }
    }

    val foodCategorySection: FoodCategorySection by lazy {
        FoodCategorySection(
            getString(R.string.food_categories),
            FoodCategoryAdapter(),
            FoodCategoryLoader(this)
        )
    }

}
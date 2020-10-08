package com.alexyuzefovich.sample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexyuzefovich.sample.databinding.ActivityMainBinding
import com.alexyuzefovich.sample.model.FoodCategory
import com.alexyuzefovich.sample.model.FoodCategorySection
import com.alexyuzefovich.sample.util.fromJson
import com.alexyuzefovich.sample.view.adapter.FoodCategoryAdapter
import com.alexyuzefovich.sample.view.adapter.SectionListAdapter

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
        binding.sections.adapter = SectionListAdapter().apply {
            submitList(sections)
        }
    }

    val foodCategorySection = FoodCategorySection(
        FoodCategoryAdapter(),
        FoodCategoryLoader(this)
    )

}
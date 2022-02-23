package com.alexyuzefovich.sample.presentation.view

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sample.R
import com.alexyuzefovich.sample.databinding.ActivityMainBinding
import com.alexyuzefovich.sample.presentation.view.model.CoffeeTimeSection
import com.alexyuzefovich.sample.presentation.view.model.FoodCategorySection
import com.alexyuzefovich.sample.presentation.view.model.TopFoodSection
import com.alexyuzefovich.sample.util.SpaceItemDecoration
import com.alexyuzefovich.sample.presentation.view.adapter.items.CoffeeAdapter
import com.alexyuzefovich.sample.presentation.view.adapter.items.FoodAdapter
import com.alexyuzefovich.sample.presentation.view.adapter.items.FoodCategoryAdapter
import com.alexyuzefovich.sample.presentation.view.adapter.sections.MultipleTypeSectionsAdapter
import com.alexyuzefovich.sample.presentation.viewmodel.MainViewModel
import dev.chrisbanes.insetter.applyInsetter

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
        enableFullScreenMode()
        initSections()
    }

    private fun enableFullScreenMode() {
        with(window) {
            WindowCompat.setDecorFitsSystemWindows(this, false)
            val statusBarColorResId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                R.color.colorWhiteAlpha85
            } else {
                R.color.colorBlackAlpha15
            }
            statusBarColor = ContextCompat.getColor(this@MainActivity, statusBarColorResId)
        }

        binding.sections.applyInsetter {
            type(statusBars = true) {
                padding()
            }
        }
    }

    private fun initSections() {
        val sections = listOf(
            createFoodCategorySection(),
            createTopFoodSection(),
            createCoffeeTimeSection()
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

    private fun createFoodCategorySection(): FoodCategorySection {
        val adapter = FoodCategoryAdapter()
        val dataExecutor = mainViewModel.createFoodCategoryDataExecutor(adapter)
        return FoodCategorySection(
            getString(R.string.food_categories_title),
            adapter,
            dataExecutor
        )
    }

    private fun createTopFoodSection(): TopFoodSection {
        val adapter = FoodAdapter { }
        val dataExecutor = mainViewModel.createTopFoodDataExecutor(adapter)
        return TopFoodSection(
            getString(R.string.top_food_title),
            adapter,
            dataExecutor
        )
    }

    private fun createCoffeeTimeSection(): CoffeeTimeSection {
        val adapter = CoffeeAdapter()
        val dataExecutor = mainViewModel.createCoffeeTimeDataExecutor(adapter)
        return CoffeeTimeSection(
            getString(R.string.coffee_time),
            adapter,
            dataExecutor
        )
    }

}
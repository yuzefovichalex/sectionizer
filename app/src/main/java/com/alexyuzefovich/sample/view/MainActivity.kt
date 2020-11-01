package com.alexyuzefovich.sample.view

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
        enableFullScreenMode()
        initSections()
    }

    private fun enableFullScreenMode() {
        with(window) {
            val systemUiVisibilityFlag: Int
            val statusBarColorResId: Int
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                systemUiVisibilityFlag = (View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                statusBarColorResId = R.color.colorWhiteAlpha85
            } else {
                systemUiVisibilityFlag = (View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                statusBarColorResId = R.color.colorBlackAlpha15
            }
            decorView.systemUiVisibility = systemUiVisibilityFlag
            statusBarColor = ContextCompat.getColor(this@MainActivity, statusBarColorResId)
        }
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
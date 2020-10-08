package com.alexyuzefovich.sample.model

import com.google.gson.annotations.SerializedName

data class FoodCategory(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String
)
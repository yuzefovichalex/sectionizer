package com.alexyuzefovich.sample.model

import com.google.gson.annotations.SerializedName

data class FoodCategory(
    @SerializedName("id")
    val id: Long,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("color")
    val color: String
)
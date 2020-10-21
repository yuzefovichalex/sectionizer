package com.alexyuzefovich.sample.model

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("ingredients")
    val ingredients: String,

    @SerializedName("cooking_time")
    val cookingTime: String,

    @SerializedName("image")
    val imageUrl: String,

    @SerializedName("rate")
    val rate: Float,

    @SerializedName("votes")
    val votes: Int,

    @SerializedName("cost")
    val cost: Float
)
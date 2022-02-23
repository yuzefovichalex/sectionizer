package com.alexyuzefovich.sample.data.model

import com.google.gson.annotations.SerializedName

data class Coffee(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val imageUrl: String,

    @SerializedName("cost")
    val cost: Float
)
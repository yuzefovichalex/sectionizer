package com.alexyuzefovich.sample.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

fun ImageView.glide(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

inline fun <reified T> Reader.fromJson(): T =
    Gson().fromJson(this, object : TypeToken<T>() { }.type)
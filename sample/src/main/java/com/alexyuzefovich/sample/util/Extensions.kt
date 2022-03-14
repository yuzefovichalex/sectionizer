package com.alexyuzefovich.sample.util

import android.content.res.Resources
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun ImageView.glide(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.glide(uri: Uri) {
    Glide.with(this)
        .load(uri)
        .into(this)
}

inline fun <reified T> Reader.fromJson(): T =
    Gson().fromJson(this, object : TypeToken<T>() { }.type)
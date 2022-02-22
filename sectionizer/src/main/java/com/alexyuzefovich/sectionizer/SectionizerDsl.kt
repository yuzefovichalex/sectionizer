package com.alexyuzefovich.sectionizer

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

fun <S : Section<*, *>> sectionsAdapter(
    createViewHolder: (parent: ViewGroup) -> SectionsAdapter.ViewHolder<S>
): SectionsAdapter<S, SectionsAdapter.ViewHolder<S>> =
    object : SectionsAdapter<S, SectionsAdapter.ViewHolder<S>>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<S> = createViewHolder(parent)
    }

fun <S : Section<*, *>> sectionViewHolder(
    itemView: View,
    sectionRecyclerView: RecyclerView,
    bind: (S) -> Unit
): SectionsAdapter.ViewHolder<S> =
    object : SectionsAdapter.ViewHolder<S>(itemView) {
        override val sectionRV: RecyclerView
            get() = sectionRecyclerView

        override fun bind(section: S) {
            bind(section)
        }
    }
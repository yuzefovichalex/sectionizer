package com.alexyuzefovich.sectionizer

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Helps to create simple [SectionsAdapter].
 *
 * @param createViewHolder Function to create ViewHolder. In simple cases you can use [sectionViewHolder].
 *
 * @return [SectionsAdapter] instance for the given [S].
 *
 * @sample
 *      sectionsAdapter<MySection> { parent ->
 *          val binding = ItemMySectionBinding.inflate(layoutInflater, parent, false)
 *
 *          sectionViewHolder(
 *              binding.list,
 *              binding.root
 *          ) { section ->
 *              with(binding) {
 *                  title.text = section.name
 *                  description.text = section.description
 *              }
 *          }
 *      }
 *
 * @author Alexander Yuzefovich
 * */
fun <S : Section<*, *>> sectionsAdapter(
    createViewHolder: (parent: ViewGroup) -> SectionsAdapter.ViewHolder<S>
): SectionsAdapter<S, SectionsAdapter.ViewHolder<S>> =
    object : SectionsAdapter<S, SectionsAdapter.ViewHolder<S>>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<S> = createViewHolder(parent)
    }

/**
 * Helps to create simple [SectionsAdapter.ViewHolder]. Can be used in pair with [sectionsAdapter].
 *
 * @param itemView Root View to pass into RecyclerView's ViewHolder.
 * @param sectionRecyclerView Section's child RecyclerView.
 * @param bind Defines how to bind Section to ViewHolder.
 *
 * @return [SectionsAdapter.ViewHolder] instance for the given [S].
 *
 * @see sectionsAdapter for more details of usage.
 *
 * @author Alexander Yuzefovich
 * */
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
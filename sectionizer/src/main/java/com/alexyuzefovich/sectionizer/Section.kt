package com.alexyuzefovich.sectionizer

import androidx.recyclerview.widget.RecyclerView

/**
 * Section is a part of the root RecyclerView, that contains another (child) RecyclerView.
 * The main goal of this class is to control child's [RecyclerView.Adapter] as well as compare
 * with another sections and hold [DataController].

 * @author Alexander Yuzefovich
 * */
abstract class Section<T, A>
        where A : SectionAdapter<T>,
              A : RecyclerView.Adapter<*>
{

    /**
     * Adapter for Section's child RecyclerView.
     * */
    abstract val adapter: A

    /**
     * DataController that should trigger data loading for the this Section.
     * */
    abstract val dataController: DataController

    /**
     * Since we use [SectionsAdapter] that is subclass of [androidx.recyclerview.widget.ListAdapter]
     * and that uses [androidx.recyclerview.widget.DiffUtil], we want to call item updates only if needed.
     * So we need to compare items to decide whether to update or not. As usual this method should
     * compare some object identifiers.
     *
     * NOTE! Both [isTheSameWith] and [isContentTheSameWith] are used to compare Section's content
     * like a title, description or other and not nested list content.
     * So, for example, don't compare adapters lists.
     *
     * @sample
     *
     * data class SectionData(
     *      val id: String,
     *      val name: String,
     *      val description: String
     * )
     *
     * class MySection(
     *      val sectionData: SectionData,
     *      ...
     * ) : Section<...> {
     *
     *      ...
     *
     *      override fun isTheSameWith(another: Section<*, *>): Boolean =
     *          another is MySection && this.sectionData.id == another.sectionData.id
     *
     *      override fun isContentTheSameWith(another: Section<*, *>): Boolean =
     *          another is MySection && this.sectionData == another.sectionData
     *
     *      ...
     *
     * }
     * */
    abstract fun isTheSameWith(another: Section<*, *>): Boolean

    /**
     * One of the methods used by [SectionsAdapter]'s [androidx.recyclerview.widget.DiffUtil].
     * See [isTheSameWith] method for more details.
     * */
    abstract fun isContentTheSameWith(another: Section<*, *>): Boolean

    /**
     * Attaches adapter to [viewHolder]'s RecyclerView if needed.
     * */
    internal fun attachAdapter(viewHolder: SectionsAdapter.ViewHolder<*>) {
        with(viewHolder) {
            val previousAdapter = sectionRV.adapter
            if (previousAdapter != adapter) {
                sectionRV.adapter = adapter
            }
        }
    }

}
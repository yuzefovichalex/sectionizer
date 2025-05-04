package com.alexyuzefovich.sectionizer

import androidx.recyclerview.widget.RecyclerView

/**
 * Section is a part of the root RecyclerView, that contains another (child) RecyclerView.
 * The main goal of this class is to control child's [RecyclerView.Adapter] as well as compare
 * with another sections and hold [DataController].

 * @author Alexander Yuzefovich
 * */
abstract class Section<T, A>
        where A : RecyclerView.Adapter<*>,
              A : SectionAdapter<T>

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
     * like a title, description or other and not nested list content. By default technically all
     * the sections are always different since we'd like to pass the inner list data to the
     * inner adapter where the actual list data comparison should be performed (e.g. via inner
     * [androidx.recyclerview.widget.ListAdapter]).
     *
     * @see hasStaticList
     * @see getDiffFrom
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
     * See [isTheSameWith] method for more details. Basically it is used to compare section data only
     * and NOT to compare section lists.
     * */
    abstract fun isContentTheSameWith(another: Section<*, *>): Boolean

    /**
     * This method is called if [isContentTheSameWith] return false allowing you to define parameters
     * that may be used to perform a partial item update. If this method is called and you return a
     * null, the full item update will be performed.
     * */
    open fun getDiffFrom(another: Section<*, *>): Any? = null

    /**
     * Whether this section hold static (persistent) list data. Default value is false, meaning,
     * the inner list data loading will be triggered each time when outer (section) list is updated.
     * If you'd like to not trigger this update, this method should return true.
     * */
    open fun hasStaticList(): Boolean = false

}
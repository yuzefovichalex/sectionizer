package com.alexyuzefovich.sample.presentation.view.model

import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sectionizer.Section
import com.alexyuzefovich.sectionizer.SectionAdapter

abstract class VerticalSection<T, A> : Section<T, A>()
        where A : RecyclerView.Adapter<*>,
              A : SectionAdapter<T>
{

    abstract val name: String

    override fun isTheSameWith(another: Section<*, *>): Boolean =
        another is VerticalSection && name == another.name

    override fun isContentTheSameWith(another: Section<*, *>): Boolean = true

}
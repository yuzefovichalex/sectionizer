package com.alexyuzefovich.sample.model

import androidx.recyclerview.widget.RecyclerView
import com.alexyuzefovich.sectionizer.Section
import com.alexyuzefovich.sectionizer.SectionAdapter

abstract class SimpleSection<T, A> : Section<T, A>()
        where A : RecyclerView.Adapter<*>,
              A : SectionAdapter<T>
{

    abstract val name: String

    override fun isTheSameWith(another: Section<*, *>): Boolean =
        another is SimpleSection && name == another.name

}
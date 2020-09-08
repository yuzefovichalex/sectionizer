package com.alexyuzefovich.sectionizer

import androidx.recyclerview.widget.RecyclerView

abstract class Section<T, A>
        where A : RecyclerView.Adapter<*>,
              A : SectionAdapter<T>
{

    abstract val adapter: A

    abstract val loaderDelegate: LoaderDelegate<T, *>

    fun loadInto(rv: RecyclerView) {
        rv.adapter = adapter
        loaderDelegate.loadData(
            successAction = {
                adapter.submitData(it)
            },
            failureAction = { }
        )
    }

}
package com.alexyuzefovich.sample.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(
    private val offset: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0
        val viewPosition = parent.getChildAdapterPosition(view)
        if (viewPosition != itemCount - 1) {
            outRect.set(0, 0, offset, 0)
        }
    }

}
package com.alexyuzefovich.sample.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private val offset: Int,

    @RecyclerView.Orientation
    private val orientation: Int
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
            when (orientation) {
                RecyclerView.VERTICAL -> outRect.set(0, 0, 0, offset)
                RecyclerView.HORIZONTAL -> outRect.set(0, 0, offset, 0)
            }
        }
    }

}
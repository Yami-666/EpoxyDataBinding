package com.example.epoxydatabinding.epoxy.item_decoration

import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class LinearVerticalDecoration(
    private val spacing: Int,
) : RecyclerView.ItemDecoration() {

    /**
     * Applies padding to all sides of the [Rect], which is the container for the view
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {

        outRect.left = spacing
        outRect.right = spacing
        outRect.top = spacing

        val isLastChild = state.itemCount - 1 == parent.getChildAdapterPosition(view)
        if (isLastChild) outRect.bottom = spacing
    }
}
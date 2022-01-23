package com.example.epoxydatabinding

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView

fun RecyclerView.getItemDecoration(): RecyclerView.ItemDecoration? {
    return if (this.itemDecorationCount > 0) {
        this.getItemDecorationAt(0)
    } else {
        null
    }
}

fun EpoxyRecyclerView.clearAllItemDecorations() {
    while (itemDecorationCount > 0) removeItemDecorationAt(0)
}

fun Fragment.showDevelopmentToast() {
    return Toast.makeText(requireContext(), R.string.in_development, Toast.LENGTH_SHORT).show()
}
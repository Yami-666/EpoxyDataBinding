package com.example.epoxydatabinding.gallery

import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.SearchView

class DynamicOnQueryTextListener(
    private val onSearchCallback: (newText: String) -> Unit,
) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?): Boolean = true
    override fun onQueryTextChange(newText: String): Boolean {
        onSearchCallback.invoke(newText)
        return true
    }

}
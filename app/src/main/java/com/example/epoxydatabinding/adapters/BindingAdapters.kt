package com.example.epoxydatabinding.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.videoFrameMicros
import coil.request.videoFrameMillis
import com.example.epoxydatabinding.R
import kotlinx.coroutines.delay
import java.io.File

@BindingAdapter("app:loadImage")
fun ImageView.loadImage(path: String?) {
    this.load(path) {
        error(R.drawable.ic_image_not_supported_24dp)
        crossfade(true)
    }


}

@BindingAdapter("app:loadVideo")
fun ImageView.loadVideo(path: String?) {
    val isExists = File(path).exists()
    this.load(File(path)) {
        videoFrameMicros(1000)
        error(R.drawable.ic_image_not_supported_24dp)
        crossfade(true)
    }
}
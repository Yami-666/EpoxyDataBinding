package com.example.epoxydatabinding.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.example.epoxydatabinding.gallery.GalleryViewModel
import com.example.epoxydatabinding.imageItem
import com.example.epoxydatabinding.models.*
import com.example.epoxydatabinding.videoItem
import java.util.*

class EpoxyGalleryController(
    private val viewModel: GalleryViewModel,
    private val listener: EpoxyListener,
) : TypedEpoxyController<List<Content>>() {
    override fun buildModels(data: List<Content>) {
        data.forEach { content ->
            when (content) {
                is Image -> addImageItem(content, viewModel)
                is Text -> addTextItem(content)
                is Video -> addVideoItem(content, listener)
                is Audio -> addAudioItem(content)
            }
        }
    }

    private fun addImageItem(content: Image, viewModel: GalleryViewModel) {
        imageItem {
            id(UUID.randomUUID().toString())
            item(content)
            onClick { _ ->
                viewModel.onGalleryItemClick(content)
            }
        }
    }

    private fun addVideoItem(content: Video, listener: EpoxyListener) {
        videoItem {
            id(UUID.randomUUID().toString())
            item(content)
            onClick { _ ->
                listener.openVideoIntent(content)
            }
        }
    }

    private fun addAudioItem(content: Audio) {
        TODO("Not yet implemented")
    }

    private fun addTextItem(content: Text) {
        TODO("Not yet implemented")
    }

    companion object {
        const val SPAN_COUNT = 2
        const val ITEMS_SPACING = 48
    }
}

interface EpoxyListener {
    fun openVideoIntent(item: Video)
}

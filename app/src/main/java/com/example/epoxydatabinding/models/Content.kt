package com.example.epoxydatabinding.models

sealed class Content {
    // Размер в Кб
    abstract val size: String
    abstract val createTime: String
    abstract val path: String
    abstract val name: String

    companion object {
        const val IMAGE_VIEW_ID = "IMAGE_VIEW_ID"
        const val TEXT_VIEW_ID = "TEXT_VIEW_ID"
        const val VIDEO_VIEW_ID = "VIDEO_VIEW_ID"
        const val AUDIO_VIEW_ID = "AUDIO_VIEW_ID"
    }
}

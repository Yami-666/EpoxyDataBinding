package com.example.epoxydatabinding.gallery

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.example.epoxydatabinding.base.BaseViewModel
import com.example.epoxydatabinding.models.Image
import com.example.epoxydatabinding.models.Video


class GalleryViewModel : BaseViewModel() {

    private val imagePaths = listOf(
        "https://img2.akspic.ru/previews/4/9/4/6/6/166494/166494-igra_v_kalmara_squid_game-500x.jpg",
        "https://klike.net/uploads/posts/2019-05/1556708032_1.jpg",
        "https://klike.net/uploads/posts/2021-01/1611131113_2.jpg",
        "https://vjoy.cc/wp-content/uploads/2020/10/dlya_dushi_35_13130628.jpg",
        "https://icdn.lenta.ru/images/2021/04/27/20/20210427205427216/original_7a6a634533af24d3fb1124510487e2be.jpg",
        "https://fresh-cards.ru/images/cards/kartinka-vse-boudet-khorosho-umor.jpg",
        "https://tipik.ru/wp-content/uploads/2019/06/%D0%9A%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B0-%D0%BD%D0%B0-%D0%B0%D0%B2%D1%83-%D1%87%D0%B5%D1%80%D0%BD%D1%8B%D0%B9-%D1%84%D0%BE%D0%BD-%D1%80%D0%B8%D1%81%D1%83%D0%BD%D0%BA%D0%B8001.jpg",
        "https://s1.1zoom.ru/big3/453/Love_Heart_Two_Moon_Trees_Silhouette_574355_3000x2000.jpg",
        "https://semantica.in/wp-content/uploads/2018/01/580b57fcd9996e24bc43c4c4-300x300.png",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbDxGWdWVDNRsiBhi3QyXFPoRb80Bgvy3bDdWeqvdueBp41v8PdFtVKAGo-81BciB9Kjo&usqp=CAU",
        "https://iecards.ru/wp-content/uploads/images/stories/virtuemart/product/kartinka-mne-25.jpg",
    ).map {
        Image(
            size = it,
            path = it,
            name = it,
            createTime = it
        )
    }

    private val videoPaths = listOf(
        "/storage/emulated/0/Download/video_2022-01-22_15-59-53.mp4",
    ).map {
        Video(
            size = it,
            path = it,
            name = it,
            createTime = it
        )
    }

    val content = MutableLiveData(imagePaths + videoPaths)

    fun onGalleryItemClick(item: Image) {
        navigate.trySend(GalleryFragmentDirections.showFullImageFragment(item.path))
    }

    fun onVideoItemClick(item: Video) {}

}
package com.example.epoxydatabinding.gallery

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.epoxydatabinding.R
import com.example.epoxydatabinding.base.BaseFragment
import com.example.epoxydatabinding.clearAllItemDecorations
import com.example.epoxydatabinding.databinding.FragmentGalleryBinding
import com.example.epoxydatabinding.epoxy.EpoxyGalleryController
import com.example.epoxydatabinding.epoxy.EpoxyGalleryController.Companion.ITEMS_SPACING
import com.example.epoxydatabinding.epoxy.EpoxyGalleryController.Companion.SPAN_COUNT
import com.example.epoxydatabinding.epoxy.EpoxyListener
import com.example.epoxydatabinding.epoxy.item_decoration.GridItemDecoration
import com.example.epoxydatabinding.epoxy.item_decoration.LinearVerticalDecoration
import com.example.epoxydatabinding.models.Image
import com.example.epoxydatabinding.models.Video
import com.example.epoxydatabinding.showDevelopmentToast

class GalleryFragment : BaseFragment<FragmentGalleryBinding, GalleryViewModel>(), EpoxyListener {
    override val layoutId: Int get() = R.layout.fragment_gallery
    override val viewModel: GalleryViewModel by viewModels()

    override fun initBinding(binding: FragmentGalleryBinding) {
        super.initBinding(binding)
        val epoxyController = EpoxyGalleryController(viewModel, this)

        binding.epoxyRecyclerView.apply {
            setController(epoxyController)
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            addItemDecoration(
                GridItemDecoration(
                    spacing = ITEMS_SPACING,
                    spanCount = SPAN_COUNT,
                    includeEdge = true
                )
            )
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.layoutManger -> changeRecyclerLayoutManager()
                else -> false
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when (menuItem.itemId) {
                R.id.item0 -> epoxyController.setData(viewModel.content.value)
                R.id.item1 -> epoxyController.setData(viewModel.content.value
                    ?.filterIsInstance<Image>())
                R.id.item2 -> epoxyController.setData(viewModel.content.value
                    ?.filterIsInstance<Video>())
                else -> {
                    menuItem.isChecked = false
                    showDevelopmentToast()
                }
            }
            binding.drawerLayout.close()
            menuItem.isChecked
        }

        val searchView: SearchView? = binding.root.findViewById(R.id.search_bar)
        val handler = Handler(Looper.getMainLooper())
        val searchListener = DynamicOnQueryTextListener { query ->
            searchItemsByQuery(handler, query, epoxyController)
        }

        searchView?.setOnQueryTextListener(searchListener)
        observeContentData(epoxyController)
    }

    private fun searchItemsByQuery(
        handler: Handler,
        query: String,
        epoxyController: EpoxyGalleryController,
    ) {
        handler.removeCallbacksAndMessages(null)

        handler.postDelayed({
            val searchItems = viewModel.content.value
                ?.filter { it.name.contains(query) }

            epoxyController.setData(searchItems)
        }, 300)
    }

    private fun changeRecyclerLayoutManager(): Boolean {
        binding.epoxyRecyclerView.apply {
            switchLayoutManager()
        }
        return true
    }

    private fun EpoxyRecyclerView.switchLayoutManager() {
        clearAllItemDecorations()

        when (this.layoutManager) {
            is GridLayoutManager -> {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(
                    LinearVerticalDecoration(
                        ITEMS_SPACING
                    )
                )
            }
            else -> {
                layoutManager = GridLayoutManager(context, SPAN_COUNT)
                addItemDecoration(
                    GridItemDecoration(
                        spacing = ITEMS_SPACING,
                        spanCount = SPAN_COUNT,
                        includeEdge = true
                    )
                )
            }
        }
    }

    private fun observeContentData(epoxyController: EpoxyGalleryController) {
        viewModel.content.observe(viewLifecycleOwner) {
            epoxyController.setData(it)
        }
    }

    override fun openVideoIntent(item: Video) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.path))
        intent.setDataAndType(Uri.parse(item.path), "video/mp4")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        requireContext().startActivity(intent)
    }
}
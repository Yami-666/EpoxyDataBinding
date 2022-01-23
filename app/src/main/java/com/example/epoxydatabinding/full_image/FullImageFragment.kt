package com.example.epoxydatabinding.full_image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.epoxydatabinding.R
import com.example.epoxydatabinding.adapters.loadImage
import com.example.epoxydatabinding.base.BaseFragment
import com.example.epoxydatabinding.databinding.FragmentFullImageBinding

class FullImageFragment : BaseFragment<FragmentFullImageBinding, FullImageViewModel>() {
    private val args: FullImageFragmentArgs by navArgs()

    override val layoutId: Int get() = R.layout.fragment_full_image
    override val viewModel: FullImageViewModel by viewModels()

    override fun initBinding(binding: FragmentFullImageBinding) {
        super.initBinding(binding)
        binding.imageView.loadImage(args.imagePath)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
package com.example.epoxydatabinding.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

abstract class BaseFragment<VB: ViewBinding, VM: BaseViewModel> : Fragment() {
    abstract val layoutId: Int
    abstract val viewModel: VM

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )
        initBinding(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.navigate.consumeEach {
                findNavController().navigate(it)
            }
        }
    }

    open fun initBinding(binding: VB) = Unit
}
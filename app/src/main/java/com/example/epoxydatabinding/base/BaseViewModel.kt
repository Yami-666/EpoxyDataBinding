package com.example.epoxydatabinding.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.Channel

abstract class BaseViewModel : ViewModel() {
    val navigate = Channel<NavDirections>()
}
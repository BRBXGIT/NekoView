package com.example.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CommonVM: ViewModel() {

    private val _selectedNavIndex = MutableStateFlow(0)
    val selectedNavIndex = _selectedNavIndex.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        0
    )

    fun setNavIndex(index: Int) {
        _selectedNavIndex.value = index
    }
}
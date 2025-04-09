package com.example.navbar_screens.home_screen.screen

import androidx.lifecycle.ViewModel
import com.example.data.domain.HomeScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    private val repository: HomeScreenRepo
): ViewModel() {


}
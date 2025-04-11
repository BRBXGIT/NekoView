package com.example.navbar_screens.home_screen.screen

import com.example.data.remote.models.titles_list_response.Item1

data class HomeScreenState(
    val titlesUpdates: List<Item1> = emptyList(),
    val isLoading: Boolean = true
)

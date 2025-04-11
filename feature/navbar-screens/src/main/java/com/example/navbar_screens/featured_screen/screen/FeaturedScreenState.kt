package com.example.navbar_screens.featured_screen.screen

import com.example.data.remote.models.titles_list_response.Item1

data class FeaturedScreenState(
    val userFeaturedTitles: List<Item1> = emptyList(),
    val isLoading: Boolean = true
)
package com.example.anime_screen.anime_screen

import com.example.data.remote.models.title_details_response.TitleDetailsResponse

data class AnimeScreenState(
    val title: TitleDetailsResponse = TitleDetailsResponse(),
    val isLoading: Boolean = true
)

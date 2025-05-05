package com.example.navbar_screens.search_screen.screen

enum class SortType {
    ByNovelty, ByPopularity
}

data class SearchScreenState(
    val releaseEnd: Boolean = false,
    val sortType: SortType = SortType.ByPopularity,
    val years: List<Int> = emptyList(),
    val season: String? = null,
    val genres: List<String> = emptyList(),
    val yearsLoading: Boolean = true,
    val genresLoading: Boolean = true,
    val selectedYears: List<Int> = emptyList(),
    val selectedGenres: List<String> = emptyList(),
    val seasons: List<String> = listOf("Зима", "Весна", "Лето", "Осень"),
    val selectedSeasons: List<String> = emptyList(),
    val queryCompleted: Boolean = false
)

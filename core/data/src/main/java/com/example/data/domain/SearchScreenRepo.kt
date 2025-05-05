package com.example.data.domain

import com.example.data.remote.models.titles_list_response.TitlesListResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result

interface SearchScreenRepo {

    suspend fun getTitlesYears(): Result<List<Int>, NetworkError>

    suspend fun getTitlesGenres(): Result<List<String>, NetworkError>

    suspend fun getTitlesByFilters(
        releaseEnd: Boolean,
        sortType: String,
        years: List<Int>,
        seasonsCodes: List<Int>,
        genres: List<String>
    ): Result<TitlesListResponse, NetworkError>
}
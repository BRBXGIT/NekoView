package com.example.data.repositories

import com.example.data.domain.SearchScreenRepo
import com.example.data.remote.ktor.SearchScreenKtorClient
import com.example.data.remote.models.titles_list_response.TitlesListResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import javax.inject.Inject

class SearchScreenRepoImpl @Inject constructor(
    private val ktorClient: SearchScreenKtorClient
): SearchScreenRepo {

    override suspend fun getTitlesYears(): Result<List<Int>, NetworkError> {
        return ktorClient.getTitlesYears()
    }

    override suspend fun getTitlesGenres(): Result<List<String>, NetworkError> {
        return ktorClient.getTitlesGenres()
    }

    override suspend fun getTitlesByFilters(
        releaseEnd: Boolean,
        sortType: String,
        years: List<Int>,
        seasonsCodes: List<Int>,
        genres: List<String>
    ): Result<TitlesListResponse, NetworkError> {
        return ktorClient.getTitlesByFilters(releaseEnd, sortType, years, seasonsCodes, genres)
    }
}
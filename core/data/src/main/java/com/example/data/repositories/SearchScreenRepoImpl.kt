package com.example.data.repositories

import com.example.data.domain.SearchScreenRepo
import com.example.data.remote.ktor.SearchScreenKtorClient
import com.example.data.remote.models.titles_genres_response.TitlesGenresResponse
import com.example.data.remote.models.titles_years_response.TitlesYearsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import javax.inject.Inject

class SearchScreenRepoImpl @Inject constructor(
    private val ktorClient: SearchScreenKtorClient
): SearchScreenRepo {

    override suspend fun getTitlesYears(): Result<TitlesYearsResponse, NetworkError> {
        return ktorClient.getTitlesYears()
    }

    override suspend fun getTitlesGenres(): Result<TitlesGenresResponse, NetworkError> {
        return ktorClient.getTitlesGenres()
    }
}
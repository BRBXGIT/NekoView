package com.example.data.repositories

import com.example.data.domain.SearchScreenRepo
import com.example.data.remote.ktor.SearchScreenKtorClient
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
}
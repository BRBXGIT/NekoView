package com.example.data.domain

import com.example.data.remote.ktor.HomeScreenKtorClient
import com.example.data.remote.models.titles_updates_response.TitlesUpdatesResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import javax.inject.Inject

class HomeScreenRepoImpl @Inject constructor(
    private val ktorClient: HomeScreenKtorClient
): HomeScreenRepo {

    override suspend fun getTitleUpdates(
        limit: Int,
        page: Int
    ): Result<TitlesUpdatesResponse, NetworkError> {
        return ktorClient.getTitlesUpdates(limit, page)
    }
}
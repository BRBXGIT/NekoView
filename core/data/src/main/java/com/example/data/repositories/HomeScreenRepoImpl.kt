package com.example.data.repositories

import com.example.data.domain.HomeScreenRepo
import com.example.data.remote.ktor.HomeScreenKtorClient
import com.example.data.remote.models.titles_list_response.TitlesListResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import javax.inject.Inject

class HomeScreenRepoImpl @Inject constructor(
    private val ktorClient: HomeScreenKtorClient
): HomeScreenRepo {

    override suspend fun getTitleUpdates(
        limit: Int,
        page: Int
    ): Result<TitlesListResponse, NetworkError> {
        return ktorClient.getTitlesUpdates(limit, page)
    }
}
package com.example.data.repositories

import com.example.data.domain.FavoritesScreenRepo
import com.example.data.remote.ktor.FavoritesScreenKtorClient
import com.example.data.remote.models.titles_list_response.TitlesListResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import javax.inject.Inject

class FavoritesScreenRepoImpl @Inject constructor(
    private val ktorClient: FavoritesScreenKtorClient
): FavoritesScreenRepo {

    override suspend fun getUserFavorites(
        sessionToken: String,
        limit: Int,
        page: Int
    ): Result<TitlesListResponse, NetworkError> {
        return ktorClient.getUserFavorites(sessionToken, limit, page)
    }
}
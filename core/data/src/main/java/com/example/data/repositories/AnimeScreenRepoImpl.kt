package com.example.data.repositories

import com.example.data.domain.AnimeScreenRepo
import com.example.data.remote.ktor.AnimeScreenKtorClient
import com.example.data.remote.models.title_details_response.TitleDetailsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import javax.inject.Inject

class AnimeScreenRepoImpl @Inject constructor(
    private val ktorClient: AnimeScreenKtorClient
): AnimeScreenRepo {

    override suspend fun getTitleById(id: Int): Result<TitleDetailsResponse, NetworkError> {
        return ktorClient.getTitleById(id)
    }
}
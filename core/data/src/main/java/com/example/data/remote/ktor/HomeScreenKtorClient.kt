package com.example.data.remote.ktor

import com.example.data.remote.models.titles_updates_response.TitlesUpdatesResponse
import com.example.data.remote.utils.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.example.data.remote.utils.Result
import com.example.data.remote.utils.Utils
import com.example.data.remote.utils.processNetworkErrors

class HomeScreenKtorClient(
    private val httpClient: HttpClient
) {
    suspend fun getTitlesUpdates(
        limit: Int,
        page: Int
    ): Result<TitlesUpdatesResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${Utils.BASE_URL}/title/updates?limit=${limit}&page=${page}"
            )
        } catch(e: kotlinx.io.IOException) { //Use IOException cause UnresolvedAddressException doesn't work
            return Result.Error(NetworkError.NO_INTERNET)
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<TitlesUpdatesResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }
}
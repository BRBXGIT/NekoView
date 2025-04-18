package com.example.data.remote.ktor

import com.example.data.remote.models.titles_list_response.TitlesListResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import com.example.data.remote.utils.Utils
import com.example.data.remote.utils.processNetworkErrors
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.network.sockets.SocketTimeoutException
import kotlinx.io.IOException

class FavoritesScreenKtorClient(
    private val httpClient: HttpClient
) {
    suspend fun getUserFavorites(
        sessionToken: String,
        limit: Int,
        page: Int
    ): Result<TitlesListResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${Utils.BASE_URL}/user/favorites?session=$sessionToken&limit=${limit}&page=${page}"
            )
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<TitlesListResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }
}
package com.example.data.remote.ktor

import com.example.data.remote.models.put_title_to_favorites_response.PutDeleteTitleToFavoritesResponse
import com.example.data.remote.models.title_details_response.TitleDetailsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import com.example.data.remote.utils.Utils
import com.example.data.remote.utils.processNetworkErrors
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.network.sockets.SocketTimeoutException
import kotlinx.io.IOException

class AnimeScreenKtorClient(
    private val httpClient: HttpClient
) {
    suspend fun getTitleById(
        id: Int
    ): Result<TitleDetailsResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${Utils.BASE_URL}/title?id=$id"
            )
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> {
                    Result.Error(NetworkError.NO_INTERNET)
                }
            }
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<TitleDetailsResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }

    suspend fun addTitleToFavorites(
        id: Int,
        sessionToken: String
    ): Result<PutDeleteTitleToFavoritesResponse, NetworkError> {
        val response = try {
            httpClient.put(
                urlString = "${Utils.BASE_URL}/user/favorites?session=$sessionToken&title_id=$id"
            )
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<PutDeleteTitleToFavoritesResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }

    suspend fun deleteTitleToFavorites(
        id: Int,
        sessionToken: String
    ): Result<PutDeleteTitleToFavoritesResponse, NetworkError> {
        val response = try {
            httpClient.delete(
                urlString = "${Utils.BASE_URL}/user/favorites?session=$sessionToken&title_id=$id"
            )
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<PutDeleteTitleToFavoritesResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }
}
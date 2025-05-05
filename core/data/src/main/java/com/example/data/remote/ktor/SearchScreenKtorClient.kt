package com.example.data.remote.ktor

import com.example.data.remote.models.titles_genres_response.TitlesGenresResponse
import com.example.data.remote.models.titles_years_response.TitlesYearsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import com.example.data.remote.utils.Utils
import com.example.data.remote.utils.processNetworkErrors
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.network.sockets.SocketTimeoutException
import kotlinx.io.IOException

class SearchScreenKtorClient(
    private val httpClient: HttpClient
) {
    suspend fun getTitlesYears(): Result<TitlesYearsResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${Utils.BASE_URL}/years"
            )
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<TitlesYearsResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }

    suspend fun getTitlesGenres(): Result<TitlesGenresResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${Utils.BASE_URL}/genres"
            )
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<TitlesGenresResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }
}
package com.example.data.remote.ktor

import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import com.example.data.remote.utils.Utils
import com.example.data.remote.utils.processNetworkErrors
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.network.sockets.SocketTimeoutException
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

class SearchScreenKtorClient(
    private val httpClient: HttpClient
) {
    suspend fun getTitlesYears(): Result<List<Int>, NetworkError> {
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
            try {
                Result.Success(response.body<List<Int>>())
            } catch(e: SerializationException) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        } else {
            processNetworkErrors(response.status.value)
        }
    }

    suspend fun getTitlesGenres(): Result<List<String>, NetworkError> {
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
            try {
                Result.Success(response.body<List<String>>())
            } catch(e: SerializationException) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        } else {
            processNetworkErrors(response.status.value)
        }
    }
}
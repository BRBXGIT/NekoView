package com.example.data.remote.ktor

import com.example.data.remote.models.user_details_response.UserDetailsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import com.example.data.remote.utils.Utils
import com.example.data.remote.utils.processNetworkErrors
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.network.sockets.SocketTimeoutException
import kotlinx.io.IOException

class SettingsScreenKtorClient(
    private val httpClient: HttpClient
) {
    suspend fun getUserDetails(
        sessionToken: String
    ): Result<UserDetailsResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${Utils.BASE_URL}/user?session=$sessionToken"
            )
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<UserDetailsResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }
}
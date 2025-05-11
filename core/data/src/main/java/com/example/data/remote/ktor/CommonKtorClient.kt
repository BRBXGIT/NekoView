package com.example.data.remote.ktor

import com.example.data.remote.models.favourites_amount_response.FavouritesAmountResponse
import com.example.data.remote.models.user_favorites_ids.UserFavoritesIdsResponse
import com.example.data.remote.models.user_session_token_response.UserSessionTokenResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import com.example.data.remote.utils.Utils
import com.example.data.remote.utils.processNetworkErrors
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Parameters
import io.ktor.network.sockets.SocketTimeoutException
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

//Contains functions that are needed in many screens at one time
class CommonKtorClient(
    private val httpClient: HttpClient
) {
    suspend fun getUserSessionToken(
        email: String,
        password: String
    ): Result<UserSessionTokenResponse, NetworkError> {
        val response = try {
            httpClient.post(
                urlString = "${Utils.BASE_AUTH_URL}/public/login.php",
            ) {
                setBody(
                    FormDataContent(
                        Parameters.build {
                            append("mail", email)
                            append("passwd", password)
                        }
                    )
                )
            }
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        val json = Json { ignoreUnknownKeys = true }
        val parsed = try {
            json.decodeFromString<UserSessionTokenResponse>(response.bodyAsText())
        } catch (_: SerializationException) {
            return Result.Error(NetworkError.UNKNOWN)
        }

        return if(response.status.value in 200..299) {
            Result.Success(parsed)
        } else {
            processNetworkErrors(response.status.value)
        }
    }

    suspend fun getUserFavoritesAmount(
        sessionToken: String
    ): Result<FavouritesAmountResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${Utils.BASE_URL}/user/favorites?session=$sessionToken&filter=pagination"
            )
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<FavouritesAmountResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }

    suspend fun getUserFavoritesIds(
        favoritesAmount: Int,
        sessionToken: String
    ): Result<UserFavoritesIdsResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${Utils.BASE_URL}/user/favorites?session=$sessionToken&filter=id&limit=$favoritesAmount"
            )
        } catch(e: IOException) {
            return when(e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        return if(response.status.value in 200..299) {
            Result.Success(response.body<UserFavoritesIdsResponse>())
        } else {
            processNetworkErrors(response.status.value)
        }
    }
}
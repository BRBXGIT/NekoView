package com.example.data.remote.ktor

import android.util.Log
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
            } catch(_: SerializationException) {
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
            } catch(_: SerializationException) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        } else {
            processNetworkErrors(response.status.value)
        }
    }

    suspend fun getTitlesByFilters(
        releaseEnd: Boolean,
        sortType: String,
        years: List<Int>,
        seasonsCodes: List<Int>,
        genres: List<String>
    ): Result<TitlesListResponse, NetworkError> {
        val queryParams = buildList {
            if (years.isNotEmpty()) {
                add("({season.year}==${years.joinToString(" or {season.year}==")})")
            }
            if (seasonsCodes.isNotEmpty()) {
                add("({season.code}==${seasonsCodes.joinToString(" or {season.code}==")})")
            }
            if (genres.isNotEmpty()) {
                add("(${genres.joinToString(" and ") { "\"$it\" in {genres}" }})")
            }
            add("(released==${releaseEnd})")
        }.joinToString(" and ")

        val url = "${Utils.BASE_URL}/title/search/advanced?query=$queryParams&order_by=$sortType&sort_direction=1"


        Log.d("CCCC", url)

        val response = try {
            httpClient.get(url)
        } catch (e: IOException) {
            return when (e) {
                is SocketTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(NetworkError.NO_INTERNET)
            }
        }

        return if (response.status.value in 200..299) {
            try {
                Result.Success(response.body<TitlesListResponse>())
            } catch (_: SerializationException) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        } else {
            processNetworkErrors(response.status.value)
        }
    }
}
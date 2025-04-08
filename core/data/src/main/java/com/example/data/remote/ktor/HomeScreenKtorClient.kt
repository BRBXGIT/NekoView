package com.example.data.remote.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.example.data.remote.utils.Result
import com.example.data.remote.utils.processNetworkErrors

//class HomeScreenKtorClient(
//    private val httpClient: HttpClient
//) {
//    suspend fun getMangaByTitle(
//        title: String?,
//        offset: Int,
//        limit: Int
//    ): Result {
//        val response = try {
//            httpClient.get()
//        } catch(e: kotlinx.io.IOException) { //Use IOException cause UnresolvedAddressException doesn't work
//            return Result.Error(NetworkError.NO_INTERNET)
//        } catch(e: ServerCloneException) {
//            return Result.Error(NetworkError.SERIALIZATION)
//        }
//
//        return if(response.status.value in 200..299) {
//            Result.Success(response.body<AllMangaResponse>())
//        } else {
//            processNetworkErrors(response.status.value)
//        }
//    }
//}
package com.example.data.domain

import com.example.data.remote.models.user_session_token_response.UserSessionTokenResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import kotlinx.coroutines.flow.Flow

interface CommonRepo {

    suspend fun getUserSessionToken(
        email: String,
        password: String
    ): Result<UserSessionTokenResponse, NetworkError>

    suspend fun saveUserSessionToken(
        token: String
    )

    fun getUserSessionTokenFromDataStore(): Flow<String>

    suspend fun saveVideoQuality(quality: Int)

    fun getVideoQuality(): Flow<Int>

    suspend fun saveSkipOpeningAutomatically(skip: Boolean)

    fun getSkipOpeningAutomatically(): Flow<Boolean>

    suspend fun saveShowSkipOpeningButton(show: Boolean)

    fun getShowSkipOpeningButton(): Flow<Boolean>

    fun getAutoplay(): Flow<Boolean>

    suspend fun saveAutoPlay(autoPlay: Boolean)
}
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
}
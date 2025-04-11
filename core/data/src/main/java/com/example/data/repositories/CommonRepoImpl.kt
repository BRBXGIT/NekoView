package com.example.data.repositories

import com.example.data.domain.CommonRepo
import com.example.data.local.data_store.NekoViewDataStore
import com.example.data.remote.ktor.CommonKtorClient
import com.example.data.remote.models.user_session_token_response.UserSessionTokenResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommonRepoImpl @Inject constructor(
    private val ktorClient: CommonKtorClient,
    private val dataStore: NekoViewDataStore
): CommonRepo {

    override suspend fun getUserSessionToken(
        email: String,
        password: String
    ): Result<UserSessionTokenResponse, NetworkError> {
        return ktorClient.getUserSessionToken(email, password)
    }

    override suspend fun saveUserSessionToken(token: String) {
        dataStore.saveUserSessionToken(token)
    }

    override fun getUserSessionTokenFromDataStore(): Flow<String> {
        return dataStore.userSessionTokenFlow
    }
}
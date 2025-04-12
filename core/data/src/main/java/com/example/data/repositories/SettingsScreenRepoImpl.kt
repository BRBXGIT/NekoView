package com.example.data.repositories

import com.example.data.domain.SettingsScreenRepo
import com.example.data.remote.ktor.SettingsScreenKtorClient
import com.example.data.remote.models.user_details_response.UserDetailsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import javax.inject.Inject

class SettingsScreenRepoImpl @Inject constructor(
    private val ktorClient: SettingsScreenKtorClient
): SettingsScreenRepo {

    override suspend fun getUserDetails(sessionToken: String): Result<UserDetailsResponse, NetworkError> {
        return ktorClient.getUserDetails(sessionToken)
    }
}
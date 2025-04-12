package com.example.data.domain

import com.example.data.remote.models.user_details_response.UserDetailsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result

interface SettingsScreenRepo {

    suspend fun getUserDetails(sessionToken: String): Result<UserDetailsResponse, NetworkError>
}
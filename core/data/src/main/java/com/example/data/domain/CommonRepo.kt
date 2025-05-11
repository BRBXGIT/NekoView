package com.example.data.domain

import com.example.data.remote.models.favourites_amount_response.FavouritesAmountResponse
import com.example.data.remote.models.user_favorites_ids.UserFavoritesIdsResponse
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

    suspend fun getUserFavoritesAmount(
        sessionToken: String
    ): Result<FavouritesAmountResponse, NetworkError>

    suspend fun getUserFavoritesIds(
        sessionToken: String,
        favoritesAmount: Int
    ): Result<UserFavoritesIdsResponse, NetworkError>

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
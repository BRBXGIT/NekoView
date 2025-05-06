package com.example.data.domain

import androidx.paging.PagingData
import com.example.data.remote.models.favourites_amount_response.FavouritesAmountResponse
import com.example.data.remote.models.titles_list_response.Item0
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import kotlinx.coroutines.flow.Flow

interface FavoritesScreenRepo {

    fun getUserFavorites(sessionToken: String = ""): Flow<PagingData<Item0>>

    suspend fun getUserFavoritesAmount(sessionToken: String): Result<FavouritesAmountResponse, NetworkError>
}
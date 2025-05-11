package com.example.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.domain.FavoritesScreenRepo
import com.example.data.remote.ktor.FavoritesScreenKtorClient
import com.example.data.remote.models.favourites_amount_response.FavouritesAmountResponse
import com.example.data.remote.models.titles_list_response.Item0
import com.example.data.remote.paging.UserFavoritesPagingSource
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesScreenRepoImpl @Inject constructor(
    private val ktorClient: FavoritesScreenKtorClient
): FavoritesScreenRepo {

    override fun getUserFavorites(sessionToken: String): Flow<PagingData<Item0>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserFavoritesPagingSource(ktorClient, sessionToken) }
        ).flow
    }
}
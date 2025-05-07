package com.example.data.domain

import com.example.data.local.watched_eps_db.TitleWatchedEps
import com.example.data.remote.models.put_title_to_favorites_response.PutTitleToFavoritesResponse
import com.example.data.remote.models.title_details_response.TitleDetailsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import kotlinx.coroutines.flow.Flow

interface AnimeScreenRepo {

    suspend fun getTitleById(id: Int): Result<TitleDetailsResponse, NetworkError>

    suspend fun addTitleToFavorites(sessionToken: String, id: Int): Result<PutTitleToFavoritesResponse, NetworkError>

    fun getWatchedEps(titleId: Int): Flow<List<TitleWatchedEps>>

    suspend fun insertTitle(title: TitleWatchedEps)

    suspend fun addWatchedEpisode(titleId: Int, episode: Int)
}
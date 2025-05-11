package com.example.data.repositories

import com.example.data.domain.AnimeScreenRepo
import com.example.data.local.watched_eps_db.TitleWatchedEps
import com.example.data.local.watched_eps_db.TitleWatchedEpsDao
import com.example.data.remote.ktor.AnimeScreenKtorClient
import com.example.data.remote.models.put_title_to_favorites_response.PutDeleteTitleToFavoritesResponse
import com.example.data.remote.models.title_details_response.TitleDetailsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class AnimeScreenRepoImpl @Inject constructor(
    private val ktorClient: AnimeScreenKtorClient,
    private val watchedEpsDao: TitleWatchedEpsDao
): AnimeScreenRepo {

    override suspend fun getTitleById(id: Int): Result<TitleDetailsResponse, NetworkError> {
        return ktorClient.getTitleById(id)
    }

    override suspend fun addTitleToFavorites(
        sessionToken: String,
        id: Int
    ): Result<PutDeleteTitleToFavoritesResponse, NetworkError> {
        return ktorClient.addTitleToFavorites(id, sessionToken)
    }

    override suspend fun deleteTitleToFavorites(
        sessionToken: String,
        id: Int
    ): Result<PutDeleteTitleToFavoritesResponse, NetworkError> {
        return ktorClient.deleteTitleToFavorites(id, sessionToken)
    }

    override suspend fun addWatchedEpisode(titleId: Int, episode: Int) {
        val currentWatched = watchedEpsDao.getWatched(titleId)
            .firstOrNull()
            ?.firstOrNull()
        val updatedEpisodes = currentWatched?.watchedEps?.toMutableSet() ?: mutableSetOf()
        updatedEpisodes.add(episode)

        val updatedTitle = TitleWatchedEps(titleId, updatedEpisodes.toList())
        watchedEpsDao.upsertTitle(updatedTitle)
    }

    override fun getWatchedEps(titleId: Int): Flow<List<TitleWatchedEps>> {
        return watchedEpsDao.getWatched(titleId)
    }

    override suspend fun insertTitle(title: TitleWatchedEps) {
        watchedEpsDao.insertTitle(title)
    }
}
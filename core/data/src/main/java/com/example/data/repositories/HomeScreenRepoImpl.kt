package com.example.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.domain.HomeScreenRepo
import com.example.data.remote.ktor.HomeScreenKtorClient
import com.example.data.remote.models.titles_list_response.Item0
import com.example.data.remote.paging.TitlesByQueryPagingSource
import com.example.data.remote.paging.TitlesUpdatesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeScreenRepoImpl @Inject constructor(
    private val ktorClient: HomeScreenKtorClient
): HomeScreenRepo {

    override fun getTitleUpdates(): Flow<PagingData<Item0>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TitlesUpdatesPagingSource(ktorClient) }
        ).flow
    }

    override suspend fun getTitleByQuery(query: String): Flow<PagingData<Item0>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TitlesByQueryPagingSource(ktorClient, query) }
        ).flow
    }
}
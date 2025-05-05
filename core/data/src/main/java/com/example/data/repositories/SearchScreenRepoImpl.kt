package com.example.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.domain.SearchScreenRepo
import com.example.data.remote.ktor.SearchScreenKtorClient
import com.example.data.remote.models.titles_list_response.Item0
import com.example.data.remote.paging.TitlesByAdvancedQueryPagingSource
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchScreenRepoImpl @Inject constructor(
    private val ktorClient: SearchScreenKtorClient
): SearchScreenRepo {

    override suspend fun getTitlesYears(): Result<List<Int>, NetworkError> {
        return ktorClient.getTitlesYears()
    }

    override suspend fun getTitlesGenres(): Result<List<String>, NetworkError> {
        return ktorClient.getTitlesGenres()
    }

    override suspend fun getTitlesByFilters(
        releaseEnd: Boolean,
        sortType: String,
        years: List<Int>,
        seasonsCodes: List<Int>,
        genres: List<String>
    ): Flow<PagingData<Item0>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TitlesByAdvancedQueryPagingSource(
                    ktorClient = ktorClient,
                    releaseEnd = releaseEnd,
                    sortType = sortType,
                    years = years,
                    seasonsCodes = seasonsCodes,
                    genres = genres
                )
            }
        ).flow
    }
}
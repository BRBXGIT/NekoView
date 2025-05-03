package com.example.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.ktor.HomeScreenKtorClient
import com.example.data.remote.models.titles_list_response.Item1
import com.example.data.remote.utils.Result

class TitlesByQueryPagingSource(
    private val ktorClient: HomeScreenKtorClient,
    private val query: String
): PagingSource<Int, Item1>() {

    override fun getRefreshKey(state: PagingState<Int, Item1>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item1> {
        val startPage = params.key ?: 1
        val limit = 5

        val response = ktorClient.getTitleByQuery(limit, startPage, query)
        return when(response) {
            is Result.Success -> {
                LoadResult.Page(
                    data = response.data.list,
                    prevKey = if (startPage == 1) null else startPage - 1,
                    nextKey = if (response.data.list.isEmpty()) null else startPage + 1
                )
            }
            is Result.Error -> {
                LoadResult.Error(Exception(response.error.name))
            }
        }
    }
}
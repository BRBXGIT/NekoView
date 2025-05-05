package com.example.data.domain

import androidx.paging.PagingData
import com.example.data.remote.models.titles_list_response.Item0
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import kotlinx.coroutines.flow.Flow

interface SearchScreenRepo {

    suspend fun getTitlesYears(): Result<List<Int>, NetworkError>

    suspend fun getTitlesGenres(): Result<List<String>, NetworkError>

    suspend fun getTitlesByFilters(
        releaseEnd: Boolean,
        sortType: String,
        years: List<Int>,
        seasonsCodes: List<Int>,
        genres: List<String>
    ): Flow<PagingData<Item0>>
}
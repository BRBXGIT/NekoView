package com.example.data.domain

import androidx.paging.PagingData
import com.example.data.remote.models.titles_list_response.Item0
import kotlinx.coroutines.flow.Flow

interface HomeScreenRepo {

    fun getTitleUpdates(): Flow<PagingData<Item0>>

    suspend fun getTitleByQuery(query: String): Flow<PagingData<Item0>>
}
package com.example.data.domain

import androidx.paging.PagingData
import com.example.data.remote.models.titles_list_response.Item1
import kotlinx.coroutines.flow.Flow

interface HomeScreenRepo {

    fun getTitleUpdates(): Flow<PagingData<Item1>>
}
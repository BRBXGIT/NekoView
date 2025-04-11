package com.example.data.domain

import com.example.data.remote.models.titles_list_response.TitlesListResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result

interface HomeScreenRepo {

    suspend fun getTitleUpdates(
        limit: Int = 10,
        page: Int = 1
    ): Result<TitlesListResponse, NetworkError>
}
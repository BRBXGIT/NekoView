package com.example.data.domain

import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result

interface SearchScreenRepo {

    suspend fun getTitlesYears(): Result<List<Int>, NetworkError>

    suspend fun getTitlesGenres(): Result<List<String>, NetworkError>
}
package com.example.data.domain

import com.example.data.remote.models.titles_genres_response.TitlesGenresResponse
import com.example.data.remote.models.titles_years_response.TitlesYearsResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result

interface SearchScreenRepo {

    suspend fun getTitlesYears(): Result<TitlesYearsResponse, NetworkError>

    suspend fun getTitlesGenres(): Result<TitlesGenresResponse, NetworkError>
}
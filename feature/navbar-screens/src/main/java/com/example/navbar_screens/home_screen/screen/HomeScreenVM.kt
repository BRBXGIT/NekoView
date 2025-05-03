package com.example.navbar_screens.home_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.data.domain.HomeScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    repository: HomeScreenRepo
) : ViewModel() {

    val titlesUpdates = repository.getTitleUpdates().cachedIn(viewModelScope)

    private val query = MutableStateFlow("")

    fun setQuery(searchBarQuery: String) {
        query.value = searchBarQuery
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val animeByQuery = query
        .flatMapLatest { query ->
            repository.getTitleByQuery(query).cachedIn(viewModelScope)
        }
}

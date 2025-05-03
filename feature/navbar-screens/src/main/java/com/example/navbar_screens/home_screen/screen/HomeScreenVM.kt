package com.example.navbar_screens.home_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.data.domain.HomeScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    repository: HomeScreenRepo
) : ViewModel() {

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        HomeScreenState()
    )

    private fun changeHomeScreenState(state: HomeScreenState) {
        _homeScreenState.value = state
    }

    val titlesUpdates = repository.getTitleUpdates().cachedIn(viewModelScope)

    private val query = MutableStateFlow("")
    private fun setQuery(searchBarQuery: String) {
        query.value = searchBarQuery
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    val animeByQuery = query
        .flatMapLatest { query ->
            repository.getTitleByQuery(query).cachedIn(viewModelScope)
        }

    fun sendIntent(intent: HomeScreenIntent) {
        when(intent) {
            is HomeScreenIntent.ChangeSearchingMode -> {
                changeHomeScreenState(
                    _homeScreenState.value.copy(isSearching = !_homeScreenState.value.isSearching)
                )
            }
            is HomeScreenIntent.SetQuery -> { setQuery(intent.query) }
        }
    }
}

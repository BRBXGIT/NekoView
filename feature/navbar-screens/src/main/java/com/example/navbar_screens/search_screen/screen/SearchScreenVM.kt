package com.example.navbar_screens.search_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.Dispatcher
import com.example.common.dispatchers.NekoViewDispatchers
import com.example.data.domain.SearchScreenRepo
import com.example.data.remote.utils.onError
import com.example.data.remote.utils.onSuccess
import com.example.design_system.snackbars.SnackbarAction
import com.example.design_system.snackbars.SnackbarController
import com.example.design_system.snackbars.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenVM @Inject constructor(
    private val repository: SearchScreenRepo,
    @Dispatcher(NekoViewDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _searchScreenState = MutableStateFlow(SearchScreenState())
    val searchScreenState = _searchScreenState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        SearchScreenState()
    )

    private fun updateScreenState(state: SearchScreenState) {
        _searchScreenState.value = state
    }

    private fun fetchTitlesGenres() {
        viewModelScope.launch(dispatcherIo) {
            _searchScreenState.update { state ->
                state.copy(genresLoading = true)
            }

            val response = repository.getTitlesGenres()
            response.onSuccess { data ->
                _searchScreenState.update { state ->
                    state.copy(
                        genresLoading = false,
                        genres = data
                    )
                }
            }
            response.onError { error ->
                _searchScreenState.update { state ->
                    state.copy(genresLoading = false)
                }
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "ERROR: $error",
                        action = SnackbarAction(
                            name = "Retry",
                            action = {
                                sendIntent(SearchScreenIntent.RetryFetchTitlesGenres)
                            }
                        )
                    )
                )
            }
        }
    }

    private fun fetchTitlesYears() {
        viewModelScope.launch(dispatcherIo) {
            _searchScreenState.update { state ->
                state.copy(yearsLoading = true)
            }

            val response = repository.getTitlesYears()
            response.onSuccess { data ->
                _searchScreenState.update { state ->
                    state.copy(
                        yearsLoading = false,
                        years = data
                    )
                }
            }
            response.onError { error ->
                _searchScreenState.update { state ->
                    state.copy(yearsLoading = false)
                }
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "ERROR: $error",
                        action = SnackbarAction(
                            name = "Retry",
                            action = {
                                sendIntent(SearchScreenIntent.RetryFetchTitlesYears)
                            }
                        )
                    )
                )
            }
        }
    }

    private fun fetchTitlesByFilters(
        releaseEnd: Boolean,
        sortType: String,
        years: List<Int>,
        seasonsCodes: List<Int>,
        genres: List<String>
    ) {
        viewModelScope.launch(dispatcherIo) {
            repository.getTitlesByFilters(releaseEnd, sortType, years, seasonsCodes, genres)
        }
    }

    fun sendIntent(intent: SearchScreenIntent) {
        when(intent) {
            is SearchScreenIntent.FetchTitlesGenres -> fetchTitlesGenres()
            is SearchScreenIntent.RetryFetchTitlesGenres -> fetchTitlesGenres()
            is SearchScreenIntent.FetchTitleYears -> fetchTitlesYears()
            is SearchScreenIntent.RetryFetchTitlesYears -> fetchTitlesYears()
            is SearchScreenIntent.UpdateScreenState -> updateScreenState(intent.state)
            is SearchScreenIntent.FetchTitlesByFilters -> {
                var seasonsCodes = mutableListOf<Int>()
                intent.selectedSeasons.forEach {
                    seasonsCodes += when(it) {
                        "Зима" -> 1
                        "Весна" -> 2
                        "Лето" -> 3
                        else -> 4
                    }
                }

                fetchTitlesByFilters(intent.releaseEnd, intent.sortType, intent.years, seasonsCodes, intent.genres)
            }
        }
    }

    init {
        sendIntent(SearchScreenIntent.FetchTitleYears)
        sendIntent(SearchScreenIntent.FetchTitlesGenres)
    }
}
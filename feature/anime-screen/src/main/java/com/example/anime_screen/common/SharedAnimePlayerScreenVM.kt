package com.example.anime_screen.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_screen.anime_screen.screen.AnimeScreenIntent
import com.example.anime_screen.anime_screen.screen.AnimeScreenState
import com.example.common.dispatchers.NekoViewDispatchers
import com.example.common.dispatchers.Dispatcher
import com.example.data.domain.AnimeScreenRepo
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

//Shared viewModel for anime screen and player screen
@HiltViewModel
class SharedAnimePlayerScreenVM @Inject constructor(
    private val repository: AnimeScreenRepo,
    @Dispatcher(NekoViewDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _animeScreenState = MutableStateFlow(AnimeScreenState())
    val animeScreenState = _animeScreenState.stateIn(
        viewModelScope,
        SharingStarted.Companion.WhileSubscribed(5_000),
        AnimeScreenState()
    )

    private fun fetchAnimeDetails(id: Int) {
        viewModelScope.launch(dispatcherIo) {
            _animeScreenState.update { state ->
                state.copy(isLoading = true)
            }

            val response = repository.getTitleById(id)
            response.onError { error ->
                _animeScreenState.update { state ->
                    state.copy(isLoading = false)
                }
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "$error",
                        action = SnackbarAction(
                            name = "Retry",
                            action = {
                                sendIntent(AnimeScreenIntent.RetryTitleDetails(id))
                            }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                _animeScreenState.update { state ->
                    state.copy(
                        title = data,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun sendIntent(intent: AnimeScreenIntent) {
        when(intent) {
            is AnimeScreenIntent.FetchTitleDetails -> fetchAnimeDetails(intent.id)
            is AnimeScreenIntent.RetryTitleDetails -> fetchAnimeDetails(intent.id)
        }
    }
}
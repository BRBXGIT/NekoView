package com.example.anime_screen.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_screen.anime_screen.screen.AnimeScreenIntent
import com.example.anime_screen.anime_screen.screen.AnimeScreenState
import com.example.common.dispatchers.Dispatcher
import com.example.common.dispatchers.NekoViewDispatchers
import com.example.data.domain.AnimeScreenRepo
import com.example.data.local.watched_eps_db.TitleWatchedEps
import com.example.data.remote.utils.onError
import com.example.data.remote.utils.onSuccess
import com.example.design_system.snackbars.SnackbarAction
import com.example.design_system.snackbars.SnackbarController
import com.example.design_system.snackbars.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
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
        SharingStarted.WhileSubscribed(5_000),
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

    private fun resetState() {
        _animeScreenState.value = AnimeScreenState()
    }

    private fun setUserSessionToken(token: String) {
        _animeScreenState.value =_animeScreenState.value.copy(
            userSessionToken = token
        )
    }

    private fun addTitleToFavorites(id: Int) {
        viewModelScope.launch(dispatcherIo) {
            val response = repository.addTitleToFavorites(
                sessionToken = _animeScreenState.value.userSessionToken,
                id = id
            )
            response.onError { error ->
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "$error",
                        action = SnackbarAction(
                            name = "Retry",
                            action = {
                                sendIntent(AnimeScreenIntent.AddTitleToFavorites(id))
                            }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                if(data.success) {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = "Добавлено в избранное",
                        )
                    )
                } else {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = "Что-то пошло не так",
                            action = SnackbarAction(
                                name = "Retry",
                                action = {
                                    sendIntent(AnimeScreenIntent.AddTitleToFavorites(id))
                                }
                            )
                        )
                    )
                }
            }
        }
    }

    private fun addTitleToWatchedEps(titleId: Int) {
        viewModelScope.launch(dispatcherIo) {
            repository.insertTitle(TitleWatchedEps(titleId))
        }
    }

    private fun addEpisodeToWatchedEps(titleId: Int, episode: Int) {
        viewModelScope.launch(dispatcherIo) {
            repository.addWatchedEpisode(titleId, episode)
        }
    }

    private fun fetchWatchedEps(titleId: Int) {
        viewModelScope.launch(dispatcherIo) {
            _animeScreenState.value = _animeScreenState.value.copy(
                watchedEps = repository.getWatchedEps(titleId).first()[0].watchedEps
            )
        }
    }

    private fun deleteTitleFromFavorites(id: Int) {
        viewModelScope.launch(dispatcherIo) {
            val response = repository.deleteTitleToFavorites(
                sessionToken = _animeScreenState.value.userSessionToken,
                id = id
            )
            response.onError { error ->
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "$error",
                        action = SnackbarAction(
                            name = "Retry",
                            action = {
                                sendIntent(AnimeScreenIntent.AddTitleToFavorites(id))
                            }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                if(data.success) {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = "Удалено из избранного",
                        )
                    )
                } else {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = "Что-то пошло не так",
                            action = SnackbarAction(
                                name = "Retry",
                                action = {
                                    sendIntent(AnimeScreenIntent.AddTitleToFavorites(id))
                                }
                            )
                        )
                    )
                }
            }
        }
    }

    fun sendIntent(intent: AnimeScreenIntent) {
        when(intent) {
            is AnimeScreenIntent.FetchTitleDetails -> fetchAnimeDetails(intent.id)
            is AnimeScreenIntent.RetryTitleDetails -> fetchAnimeDetails(intent.id)
            is AnimeScreenIntent.ResetScreenState -> resetState()
            is AnimeScreenIntent.SetUserSessionToken -> setUserSessionToken(intent.token)
            is AnimeScreenIntent.AddTitleToFavorites -> addTitleToFavorites(intent.id)
            is AnimeScreenIntent.AddTitleToWatchedEps -> addTitleToWatchedEps(intent.titleId)
            is AnimeScreenIntent.AddEpisodeToWatchedEps -> addEpisodeToWatchedEps(intent.titleId, intent.episode)
            is AnimeScreenIntent.FetchWatchedEps -> fetchWatchedEps(intent.titleId)
            is AnimeScreenIntent.DeleteTitleFromFavorites -> deleteTitleFromFavorites(intent.titleId)
        }
    }
}
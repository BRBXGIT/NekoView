package com.example.navbar_screens.home_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.AniKunDispatchers
import com.example.common.dispatchers.Dispatcher
import com.example.common.functions.processNetworkErrorsForUi
import com.example.data.domain.HomeScreenRepo
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
class HomeScreenVM @Inject constructor(
    private val repository: HomeScreenRepo,
    @Dispatcher(AniKunDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.stateIn(
        viewModelScope,
        SharingStarted.Lazily, //Lazily to don't rewrite value every single time screen recomposed
        HomeScreenState()
    )

    private val limit = 10
    private var page = 1

    private fun fetchTitlesUpdates() {
        viewModelScope.launch(dispatcherIo) {
            _homeScreenState.update { it.copy(isLoading = true) }
            val response = repository.getTitleUpdates(limit, page)
            response.onError { error ->
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = processNetworkErrorsForUi(error),
                        action = SnackbarAction(
                            name = "Retry",
                            action = { sendIntent(HomeScreenIntent.RetryFetch) }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                _homeScreenState.update { state ->
                    state.copy(
                        titlesUpdates = state.titlesUpdates + data.list,
                        isLoading = false
                    )
                }
                page += 1
            }
        }
    }

    fun sendIntent(intent: HomeScreenIntent) {
        when(intent) {
            is HomeScreenIntent.LoadTitles -> fetchTitlesUpdates()
            is HomeScreenIntent.RetryFetch -> fetchTitlesUpdates()
        }
    }

    init {
        sendIntent(HomeScreenIntent.LoadTitles)
    }
}
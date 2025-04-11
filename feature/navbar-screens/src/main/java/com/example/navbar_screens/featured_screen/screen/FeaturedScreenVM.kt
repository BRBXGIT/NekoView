package com.example.navbar_screens.featured_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.AniKunDispatchers
import com.example.common.dispatchers.Dispatcher
import com.example.common.functions.processNetworkErrorsForUi
import com.example.data.domain.FavoritesScreenRepo
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
class FeaturedScreenVM @Inject constructor(
    private val repository: FavoritesScreenRepo,
    @Dispatcher(AniKunDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _featuredScreenState = MutableStateFlow(FeaturedScreenState())
    val featuredScreenState = _featuredScreenState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        FeaturedScreenState()
    )

    private val limit = 10
    private var page = 1

    private fun fetchTitlesUpdates(sessionToken: String) {
        viewModelScope.launch(dispatcherIo) {
            _featuredScreenState.update { it.copy(isLoading = true) }
            val response = repository.getUserFavorites(sessionToken, limit, page)
            response.onError { error ->
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = processNetworkErrorsForUi(error),
                        action = SnackbarAction(
                            name = "Retry",
                            action = {
                                sendIntent(
                                    FeatureScreenIntent.RetryFetch(sessionToken)
                                )
                            }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                _featuredScreenState.update { state ->
                    state.copy(
                        userFeaturedTitles = state.userFeaturedTitles + data.list,
                        isLoading = false
                    )
                }
                page += 1
            }
        }
    }

    fun sendIntent(intent: FeatureScreenIntent) {
        when(intent) {
            is FeatureScreenIntent.LoadUserFeaturedTitles -> fetchTitlesUpdates(intent.sessionToken)
            is FeatureScreenIntent.RetryFetch -> fetchTitlesUpdates(intent.sessionToken)
        }
    }
}
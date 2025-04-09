package com.example.navbar_screens.home_screen.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.AniKunDispatchers
import com.example.common.dispatchers.Dispatcher
import com.example.common.functions.processNetworkErrorsForUi
import com.example.data.domain.HomeScreenRepo
import com.example.data.remote.models.titles_updates_response.Item1
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
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO REWRITE TO MVI
@HiltViewModel
class HomeScreenVM @Inject constructor(
    private val repository: HomeScreenRepo,
    @Dispatcher(AniKunDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    init {
        fetchTitlesUpdates()
    }

    //Something like a paging :)
    private val _titlesUpdates = MutableStateFlow<List<Item1>>(emptyList())
    val titlesUpdates = _titlesUpdates.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )
    private val _titlesUpdatesLoading = MutableStateFlow(true)
    val titlesUpdatesLoading = _titlesUpdatesLoading.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        true
    )

    private val limit = 10
    private var page = 1

    fun fetchTitlesUpdates() {
        viewModelScope.launch(dispatcherIo) {
            val response = repository.getTitleUpdates(limit, page)
            response.onError { error ->
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = processNetworkErrorsForUi(error),
                        action = SnackbarAction(
                            name = "Refresh",
                            action = { fetchTitlesUpdates() }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                _titlesUpdates.value += data.list
                _titlesUpdatesLoading.value = false
                page += limit
            }
        }
    }
}
package com.example.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.Dispatcher
import com.example.common.dispatchers.NekoViewDispatchers
import com.example.data.domain.CommonRepo
import com.example.data.remote.models.user_favorites_ids.UserFavoritesIdsResponse
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
class CommonVM @Inject constructor(
    private val repository: CommonRepo,
    @Dispatcher(NekoViewDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _userFavoritesIds = MutableStateFlow(UserFavoritesIdsResponse())
    val userFavoritesIds = _userFavoritesIds.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        UserFavoritesIdsResponse()
    )

    private fun fetchUserFavoriteTitleIds(
        favoritesAmount: Int,
        sessionToken: String
    ) {
        viewModelScope.launch(dispatcherIo) {
            val response = repository.getUserFavoritesIds(sessionToken, favoritesAmount)
            response.onError { error ->
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "ERROR: $error, ошибка загрузки избранных",
                        action = SnackbarAction(
                            name = "Retry",
                            action = { fetchUserFavoritesAmount(sessionToken) }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                _userFavoritesIds.value = data
            }
        }
    }

    private fun fetchUserFavoritesAmount(sessionToken: String) {
        viewModelScope.launch(dispatcherIo) {
            val response = repository.getUserFavoritesAmount(sessionToken)
            response.onError { error ->
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "ERROR: $error, ошибка загрузки избранных",
                        action = SnackbarAction(
                            name = "Retry",
                            action = { fetchUserFavoritesAmount(sessionToken) }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                fetchUserFavoriteTitleIds(data.pagination.totalItems, sessionToken)
            }
        }
    }

    private val _favoritesNeedReload = MutableStateFlow(false)
    val favoritesNeedReload = _favoritesNeedReload.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        false
    )

    private fun setFavoritesReload(reload: Boolean) {
        _favoritesNeedReload.value = reload
    }

    private val _commonState = MutableStateFlow(CommonState())
    val commonState = _commonState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        CommonState()
    )

    private fun fetchUserTokenFromDataStore() {
        viewModelScope.launch(dispatcherIo) {
            repository.getUserSessionTokenFromDataStore().collect { sessionToken ->
                _commonState.update { state ->
                    state.copy(
                        sessionToken = sessionToken
                    )
                }
                if(sessionToken != "") {
                    fetchUserFavoritesAmount(_commonState.value.sessionToken)
                }
            }
        }
    }

    private fun setNavIndex(index: Int) {
        _commonState.update { state ->
            state.copy(index)
        }
    }

    private fun getUserToken(
        email: String,
        password: String,
    ) {
        viewModelScope.launch(dispatcherIo) {
            _commonState.update { state ->
                state.copy(isSessionTokenLoading = true)
            }

            val response = repository.getUserSessionToken(email, password)
            response.onError { error ->
                _commonState.update { state ->
                    state.copy(isSessionTokenLoading = false)
                }

                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "ERROR: $error",
                        action = SnackbarAction(
                            name = "Retry",
                            action = { getUserToken(email, password) }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                _commonState.update { state ->
                    state.copy(isSessionTokenLoading = false)
                }

                if(data.err == "ok") {
                    repository.saveUserSessionToken(data.sessionId)
                } else if(data.err == "error") {
                    if(data.key == "invalidUser") {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = "Check you email and password :)"
                            )
                        )
                    } else {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = "Something went wrong on server",
                                action = SnackbarAction(
                                    name = "Retry",
                                    action = { getUserToken(email, password) }
                                )
                            )
                        )
                    }
                }
            }
        }
    }

    fun sendIntent(intent: CommonIntent) {
        when(intent) {
            is CommonIntent.SetNavIndex -> {
                setNavIndex(intent.index)
            }
            is CommonIntent.GetUserToken -> {
                getUserToken(
                    email = intent.email,
                    password = intent.password
                )
            }
            is CommonIntent.ChangeAutoSkipOpening -> { changeAutoSkipOpening() }
            is CommonIntent.ChangeAutoplay -> { changeAutoplay() }
            is CommonIntent.ChangeShowSkipOpeningButton -> { changeShowSkipOpeningButton() }
            is CommonIntent.ChangeVideoQuality -> { changeVideoQuality(intent.quality) }
            is CommonIntent.FetchAutoPlay -> { fetchAutoplay() }
            is CommonIntent.FetchAutoSkipOpening -> { fetchAutoSkipOpening() }
            is CommonIntent.FetchShowSkipOpeningButton -> { fetchShowSkipOpeningButton() }
            is CommonIntent.FetchVideoQuality -> { fetchVideoQuality() }
            is CommonIntent.FavoritesNeedReload -> { setFavoritesReload(intent.reload) }
            is CommonIntent.ChangeFeatured -> {
                _userFavoritesIds.value = _userFavoritesIds.value.copy(
                    list = intent.featured
                )
            }
        }
    }

    private val _videoQuality = MutableStateFlow<Int?>(null)
    val videoQuality = _videoQuality.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    private fun fetchVideoQuality() {
        viewModelScope.launch(dispatcherIo) {
            repository.getVideoQuality().collect {
                _videoQuality.value = it
            }
        }
    }

    private fun changeVideoQuality(quality: Int) {
        viewModelScope.launch(dispatcherIo) {
            repository.saveVideoQuality(quality)
        }
        fetchVideoQuality()
    }

    private val _showSkipOpeningButton = MutableStateFlow<Boolean?>(null)
    val showSkipOpeningButton = _showSkipOpeningButton.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    private fun fetchShowSkipOpeningButton() {
        viewModelScope.launch(dispatcherIo) {
            repository.getShowSkipOpeningButton().collect {
                _showSkipOpeningButton.value = it
            }
        }
    }

    private fun changeShowSkipOpeningButton() {
        viewModelScope.launch(dispatcherIo) {
            repository.saveShowSkipOpeningButton(!_showSkipOpeningButton.value!!)
        }
        fetchShowSkipOpeningButton()
    }

    private val _autoSkipOpening = MutableStateFlow<Boolean?>(null)
    val autoSkipOpening = _autoSkipOpening.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    private fun fetchAutoSkipOpening() {
        viewModelScope.launch(dispatcherIo) {
            repository.getSkipOpeningAutomatically().collect {
                _autoSkipOpening.value = it
            }
        }
    }

    private fun changeAutoSkipOpening() {
        viewModelScope.launch(dispatcherIo) {
            repository.saveSkipOpeningAutomatically(!_autoSkipOpening.value!!)
        }
        fetchAutoSkipOpening()
    }

    private val _autoPlay = MutableStateFlow<Boolean?>(null)
    val autoPlay = _autoPlay.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    private fun changeAutoplay() {
        viewModelScope.launch(dispatcherIo) {
            repository.saveAutoPlay(!_autoPlay.value!!)
        }
        fetchAutoSkipOpening()
    }

    private fun fetchAutoplay() {
        viewModelScope.launch(dispatcherIo) {
            repository.getAutoplay().collect {
                _autoPlay.value = it
            }
        }
    }

    init {
        fetchUserTokenFromDataStore()
    }
}
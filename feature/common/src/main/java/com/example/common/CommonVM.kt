package com.example.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.AniKunDispatchers
import com.example.common.dispatchers.Dispatcher
import com.example.data.domain.CommonRepo
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
    @Dispatcher(AniKunDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

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
            is CommonIntent.SetNavIndex -> setNavIndex(intent.index)
            is CommonIntent.GetUserToken -> {
                getUserToken(
                    email = intent.email,
                    password = intent.password
                )
            }
        }
    }

    init {
        fetchUserTokenFromDataStore()
    }
}
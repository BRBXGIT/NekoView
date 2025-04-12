package com.example.navbar_screens.settings_screen.screen

import androidx.lifecycle.ViewModel
import com.example.data.domain.SettingsScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.AniKunDispatchers
import com.example.common.dispatchers.Dispatcher
import com.example.data.remote.utils.onError
import com.example.data.remote.utils.onSuccess
import com.example.design_system.snackbars.SnackbarAction
import com.example.design_system.snackbars.SnackbarController
import com.example.design_system.snackbars.SnackbarEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsScreenVM @Inject constructor(
    private val repository: SettingsScreenRepo,
    @Dispatcher(AniKunDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _settingsScreenState = MutableStateFlow(SettingsScreenState())
    val settingsScreenState = _settingsScreenState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        SettingsScreenState()
    )

    fun fetchUserDetails(sessionToken: String) {
        viewModelScope.launch(dispatcherIo) {
            _settingsScreenState.update { state ->
                state.copy(userDetailsLoading = true)
            }

            val response = repository.getUserDetails(sessionToken)
            response.onSuccess { data ->
                _settingsScreenState.update { state ->
                    state.copy(
                        userName = data.nickname,
                        userImageUrl = data.avatarOriginal,
                        userDetailsLoading = false
                    )
                }
            }
            response.onError { error ->
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "ERROR: $error",
                        action = SnackbarAction(
                            name = "Retry",
                            action = {
                                sendIntent(
                                    SettingsScreenIntent.RetryUserDetails(sessionToken)
                                )
                            }
                        )
                    )
                )
            }
        }
    }

    fun sendIntent(intent: SettingsScreenIntent) {
        when(intent) {
            is SettingsScreenIntent.LoadUserDetails -> fetchUserDetails(intent.sessionToken)
            is SettingsScreenIntent.RetryUserDetails -> fetchUserDetails(intent.sessionToken)
        }
    }
}
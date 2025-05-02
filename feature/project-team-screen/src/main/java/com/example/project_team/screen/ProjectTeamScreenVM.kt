package com.example.project_team.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.Dispatcher
import com.example.common.dispatchers.NekoViewDispatchers
import com.example.data.domain.ProjectTeamScreenRepo
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
class ProjectTeamScreenVM @Inject constructor(
    private val repository: ProjectTeamScreenRepo,
    @Dispatcher(NekoViewDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _projectTeamScreenState = MutableStateFlow(ProjectTeamScreenState())
    val projectTeam = _projectTeamScreenState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ProjectTeamScreenState()
    )

    private fun fetchProjectTeam() {
        viewModelScope.launch(dispatcherIo) {
            _projectTeamScreenState.update { state ->
                state.copy(isLoading = true)
            }

            val response = repository.getProjectTeam()
            response.onError { error ->
                _projectTeamScreenState.update { state ->
                    state.copy(isLoading = false)
                }
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "$error",
                        action = SnackbarAction(
                            name = "Retry",
                            action = {
                                sendIntent(ProjectTeamScreenIntent.RetryFetchProjectTeam)
                            }
                        )
                    )
                )
            }
            response.onSuccess { data ->
                _projectTeamScreenState.update { state ->
                    state.copy(
                        projectTeam = data,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun sendIntent(intent: ProjectTeamScreenIntent) {
        when(intent) {
            ProjectTeamScreenIntent.FetchProjectTeam -> fetchProjectTeam()
            ProjectTeamScreenIntent.RetryFetchProjectTeam -> fetchProjectTeam()
        }
    }

    init {
        sendIntent(ProjectTeamScreenIntent.FetchProjectTeam)
    }
}
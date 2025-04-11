package com.example.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.AniKunDispatchers
import com.example.common.dispatchers.Dispatcher
import com.example.data.domain.CommonRepo
import com.example.data.remote.utils.onError
import com.example.data.remote.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonVM @Inject constructor(
    private val repository: CommonRepo,
    @Dispatcher(AniKunDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _selectedNavIndex = MutableStateFlow(0)
    val selectedNavIndex = _selectedNavIndex.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        0
    )

    fun setNavIndex(index: Int) {
        _selectedNavIndex.value = index
    }

    val userSessionToken = repository.getUserSessionTokenFromDataStore().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        ""
    )

    fun getUserToken(
        email: String,
        password: String,
        onError: () -> Unit,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch(dispatcherIo) {
            val response = repository.getUserSessionToken(email, password)
            response.onError { error ->
                onError()
            }
            response.onSuccess { data ->
                if(data.err == "ok") {
                    repository.saveUserSessionToken(data.key)
                    onSuccess()
                } else {
                    onError()
                }
            }
        }
    }
}
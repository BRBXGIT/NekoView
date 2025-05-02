package com.example.design_system.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.Dispatcher
import com.example.common.dispatchers.NekoViewDispatchers
import com.example.data.local.data_store.NekoViewDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppThemeVM @Inject constructor(
    private val dataStore: NekoViewDataStore,
    @Dispatcher(NekoViewDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    val theme = dataStore.themeFlow
    val colorSystem = dataStore.colorSystemFlow

    fun changeTheme(theme: String) {
        viewModelScope.launch(dispatcherIo) {
            dataStore.saveTheme(theme)
        }
    }

    fun changeColorSystem(colorSystem: String) {
        viewModelScope.launch(dispatcherIo) {
            dataStore.saveColorSystem(colorSystem)
        }
    }
}
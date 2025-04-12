package com.example.navbar_screens.featured_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.common.dispatchers.AniKunDispatchers
import com.example.common.dispatchers.Dispatcher
import com.example.data.domain.FavoritesScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.updateAndGet
import javax.inject.Inject

@HiltViewModel
class FeaturedScreenVM @Inject constructor(
    repository: FavoritesScreenRepo
): ViewModel() {

    private val _userSessionToken = MutableStateFlow<String>("")
    fun setUserSessionToken(sessionToken: String) {
        _userSessionToken.value = sessionToken
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val userFavorites = _userSessionToken
        .filter { it.isNotEmpty() }
        .flatMapLatest { token ->
            repository.getUserFavorites(token)
        }
        .cachedIn(viewModelScope)
}
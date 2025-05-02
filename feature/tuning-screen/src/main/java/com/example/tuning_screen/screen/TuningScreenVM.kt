package com.example.tuning_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.Dispatcher
import com.example.common.dispatchers.NekoViewDispatchers
import com.example.data.domain.CommonRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TuningScreenVM @Inject constructor(
    private val commonRepo: CommonRepo,
    @Dispatcher(NekoViewDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _videoQuality = MutableStateFlow<Int?>(null)
    val videoQuality = _videoQuality.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    private fun fetchVideoQuality() {
        viewModelScope.launch(dispatcherIo) {
            commonRepo.getVideoQuality().collect {
                _videoQuality.value = it
            }
        }
    }

    private fun changeVideoQuality(quality: Int) {
        viewModelScope.launch(dispatcherIo) {
            commonRepo.saveVideoQuality(quality)
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
            commonRepo.getShowSkipOpeningButton().collect {
                _showSkipOpeningButton.value = it
            }
        }
    }

    private fun setShowSkipOpeningButton() {
        viewModelScope.launch(dispatcherIo) {
            commonRepo.saveShowSkipOpeningButton(!_showSkipOpeningButton.value!!)
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
            commonRepo.getSkipOpeningAutomatically().collect {
                _autoSkipOpening.value = it
            }
        }
    }

    private fun setAutoSkipOpening() {
        viewModelScope.launch(dispatcherIo) {
            commonRepo.saveSkipOpeningAutomatically(!_autoSkipOpening.value!!)
        }
        fetchAutoSkipOpening()
    }

    private val _autoPlay = MutableStateFlow<Boolean?>(null)
    val autoPlay = _autoPlay.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    private fun setAutoplay() {
        viewModelScope.launch(dispatcherIo) {
            commonRepo.saveAutoPlay(!_autoPlay.value!!)
        }
        fetchAutoSkipOpening()
    }

    private fun fetchAutoplay() {
        viewModelScope.launch(dispatcherIo) {
            commonRepo.getAutoplay().collect {
                _autoPlay.value = it
            }
        }
    }
}
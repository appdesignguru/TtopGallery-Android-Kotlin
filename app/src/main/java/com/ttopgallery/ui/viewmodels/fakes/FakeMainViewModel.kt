package com.ttopgallery.ui.viewmodels.fakes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ttopgallery.ui.states.MainUiState
import com.ttopgallery.ui.viewmodels.interfaces.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** Fake MainViewModel implementation class. */
@HiltViewModel
class FakeMainViewModel @Inject constructor() : ViewModel(), MainViewModel {

    override fun getMainUiState(): MutableLiveData<MainUiState> {
        val mainUiState = MutableLiveData<MainUiState>()
        mainUiState.value = MainUiState(false, false)
        return mainUiState
    }
}
package com.ttopgallery.ui.viewmodels.interfaces

import androidx.lifecycle.MutableLiveData
import com.ttopgallery.ui.states.MainUiState

/** State holder interface for the main UI screen. */
interface MainViewModel {

    /** Returns MainUiState.  */
    fun getMainUiState(): MutableLiveData<MainUiState>

}
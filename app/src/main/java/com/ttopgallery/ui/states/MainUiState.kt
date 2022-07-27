package com.ttopgallery.ui.states

/** UI state for main screen. */
data class MainUiState(

    /** Checks whether authentication is being currently fetched. */
    val isFetchingAuthenticationState: Boolean,

    /** Checks whether active login token exists. */
    val hasActiveLoginToken: Boolean
)

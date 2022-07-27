package com.ttopgallery.ui.states

/** Base UI state class for Authentication UI screens. */
open class AuthenticationUiState(

    /** Returns authentication ui status. */
    val authenticationUiStatus: AuthenticationUiStatus,

    /** Returns input validation error message. */
    val errorMessage: String
)

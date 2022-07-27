package com.ttopgallery.ui.states

/** UI state for change password screen.  */
class ChangePasswordUiState(
    authenticationUiStatus: AuthenticationUiStatus,
    errorMessage: String
) : AuthenticationUiState(
    authenticationUiStatus,
    errorMessage
)
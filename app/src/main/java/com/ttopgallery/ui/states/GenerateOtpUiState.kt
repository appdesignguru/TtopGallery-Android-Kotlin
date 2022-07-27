package com.ttopgallery.ui.states

import com.ttopgallery.localdatasources.entities.OtpType

class GenerateOtpUiState (
    authenticationUiStatus: AuthenticationUiStatus,
    errorMessage: String,
    val otpType: OtpType
) : AuthenticationUiState(
    authenticationUiStatus,
    errorMessage
)
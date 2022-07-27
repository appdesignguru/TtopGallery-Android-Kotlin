package com.ttopgallery.ui.viewmodels.interfaces

import androidx.lifecycle.MutableLiveData
import com.ttopgallery.localdatasources.entities.OtpType
import com.ttopgallery.ui.states.*

/** State holder interface for the authentication UI screens.  */
interface AuthenticationViewModel {

    /** Returns LoginUiState.  */
    fun getLoginUiState(): MutableLiveData<LoginUiState>

    /** Returns GenerateOtpUiState.  */
    fun getGenerateOtpUiState(): MutableLiveData<GenerateOtpUiState>

    /** Returns GenerateUiState.  */
    fun getRegistrationUiState(): MutableLiveData<RegistrationUiState>

    /** Returns ResetPasswordUiState.  */
    fun getResetPasswordUiState(): MutableLiveData<ResetPasswordUiState>

    /** Returns ChangePasswordUiState.  */
    fun getChangePasswordUiState(): MutableLiveData<ChangePasswordUiState>

    /** Attempts to login user.  */
    fun login(email: String, password: String)

    /** Attempts to generate one-time-password.  */
    fun generateOtp(email: String)

    /** Attempts to register user.  */
    fun register(password: String, confirmPassword: String, otpCode: String)

    /** Attempts to reset password.  */
    fun resetPassword(newPassword: String, confirmNewPassword: String, otpCode: String)

    /** Attempts to change password  */
    fun changePassword(email: String, currentPassword: String, newPassword: String, confirmNewPassword: String)

    /** Sets otpType.  */
    fun setOtpType(otpType: OtpType)
}

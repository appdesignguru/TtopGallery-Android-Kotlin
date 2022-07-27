package com.ttopgallery.ui.viewmodels.fakes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ttopgallery.localdatasources.entities.OtpType
import com.ttopgallery.ui.states.*
import com.ttopgallery.ui.viewmodels.interfaces.AuthenticationViewModel
import javax.inject.Inject

class FakeAuthenticationViewModel @Inject constructor() : ViewModel(), AuthenticationViewModel{

    private val loginUiState = MutableLiveData<LoginUiState>()
    private val generateOtpUiState = MutableLiveData<GenerateOtpUiState>()
    private val registrationUiState = MutableLiveData<RegistrationUiState>()
    private val resetPasswordUiState = MutableLiveData<ResetPasswordUiState>()
    private val changePasswordUiState = MutableLiveData<ChangePasswordUiState>()
    private lateinit var otpType: OtpType

    override fun getLoginUiState(): MutableLiveData<LoginUiState> {
        loginUiState.value = LoginUiState(AuthenticationUiStatus.Idle, "")
        return loginUiState
    }

    override fun getGenerateOtpUiState(): MutableLiveData<GenerateOtpUiState> {
        generateOtpUiState.value = GenerateOtpUiState(AuthenticationUiStatus.Idle, "", otpType)
        return generateOtpUiState
    }

    override fun getRegistrationUiState(): MutableLiveData<RegistrationUiState> {
        registrationUiState.value = RegistrationUiState(AuthenticationUiStatus.Idle, "")
        return registrationUiState
    }

    override fun getResetPasswordUiState(): MutableLiveData<ResetPasswordUiState> {
        resetPasswordUiState.value = ResetPasswordUiState(AuthenticationUiStatus.Idle, "")
        return resetPasswordUiState
    }

    override fun getChangePasswordUiState(): MutableLiveData<ChangePasswordUiState> {
        changePasswordUiState.value = ChangePasswordUiState(AuthenticationUiStatus.Idle, "")
        return changePasswordUiState
    }

    override fun login(email: String, password: String) {
        //Assumes successful login
        loginUiState.value = LoginUiState(AuthenticationUiStatus.LoginSuccessful, "")
    }

    override fun generateOtp(email: String) {
        //Assumes successful otp generation
        generateOtpUiState.value = GenerateOtpUiState(AuthenticationUiStatus.OtpGeneratedSuccessfully, "", otpType)
    }

    override fun register(password: String, confirmPassword: String, otpCode: String) {
        //Assumes successful user registration
        registrationUiState.value = RegistrationUiState(AuthenticationUiStatus.LoginSuccessful, "")
    }

    override fun resetPassword(newPassword: String, confirmNewPassword: String, otpCode: String) {
        //Assumes successful password reset
        resetPasswordUiState.value = ResetPasswordUiState(AuthenticationUiStatus.LoginSuccessful, "")
    }

    override fun changePassword(email: String, currentPassword: String,
                                newPassword: String, confirmNewPassword: String) {
        //Assumes successful password change
        changePasswordUiState.value =
            ChangePasswordUiState(AuthenticationUiStatus.ChangePasswordSuccessful, "")
    }

    override fun setOtpType(otpType: OtpType) {
        this.otpType = otpType
    }
}
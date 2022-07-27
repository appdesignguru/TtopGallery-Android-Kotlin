package com.ttopgallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ttopgallery.R
import com.ttopgallery.localdatasources.entities.OtpType
import com.ttopgallery.ui.states.AuthenticationUiStatus
import com.ttopgallery.ui.states.GenerateOtpUiState
import com.ttopgallery.ui.viewmodels.fakes.FakeAuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

/** Fragment for generating otp. */
@AndroidEntryPoint
class GenerateOtpFragment : Fragment() {

    private val authenticationViewModel by activityViewModels<FakeAuthenticationViewModel>()
    private lateinit var emailEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_generate_otp, container, false)

        emailEditText = view.findViewById(R.id.generate_otp_fragment_EditTextTextEmailAddress)
        submitButton = view.findViewById(R.id.generate_otp_fragment_submitButton)
        progressBar = view.findViewById(R.id.generate_otp_fragment_progressBar)
        errorTextView = view.findViewById(R.id.generate_otp_fragment_error_textView)

        submitButton.setOnClickListener {
            authenticationViewModel.generateOtp(emailEditText.text.toString())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel.getGenerateOtpUiState().observe(viewLifecycleOwner) {
                result: GenerateOtpUiState ->
                updateUI(result.authenticationUiStatus, result.otpType, result.errorMessage)
        }
    }

    private fun updateUI(authenticationUiStatus: AuthenticationUiStatus,otpType: OtpType, errorMessage: String) {
        when (authenticationUiStatus) {
            AuthenticationUiStatus.GeneratingOtp -> {
                progressBar.visibility = View.VISIBLE
                submitButton.isEnabled = false
            }
            AuthenticationUiStatus.OtpGeneratedSuccessfully -> navigateToAppropriateFragment(otpType)
            AuthenticationUiStatus.InvalidInput, AuthenticationUiStatus.Failure -> {
                errorTextView.text = errorMessage
                progressBar.visibility = View.GONE
                submitButton.isEnabled = true
            }
            else -> {
                progressBar.visibility = View.GONE
                progressBar.isEnabled = true
            }
        }
    }

    private fun navigateToAppropriateFragment(otpType: OtpType) {
        if (otpType === OtpType.Registration) {
            navigateToRegistrationFragment()
        } else if (otpType === OtpType.ResetPassword) {
            navigateToResetPasswordFragment()
        }
    }

    private fun navigateToRegistrationFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.authentication_activity_fragment_container_view,
                RegistrationFragment::class.java, null)
            .setReorderingAllowed(true)
            .addToBackStack(null) // name can be null
            .commit()
    }

    private fun navigateToResetPasswordFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.authentication_activity_fragment_container_view,
                ResetPasswordFragment::class.java, null)
            .setReorderingAllowed(true)
            .addToBackStack(null) // name can be null
            .commit()
    }
}
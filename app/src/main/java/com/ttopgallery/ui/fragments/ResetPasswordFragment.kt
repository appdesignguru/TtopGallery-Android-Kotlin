package com.ttopgallery.ui.fragments

import android.content.Intent
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
import com.ttopgallery.ui.activities.GalleryActivity
import com.ttopgallery.ui.states.AuthenticationUiStatus
import com.ttopgallery.ui.states.ResetPasswordUiState
import com.ttopgallery.ui.viewmodels.fakes.FakeAuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

/** Fragment for resetting user password. */
@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {

    private val authenticationViewModel by activityViewModels<FakeAuthenticationViewModel>()
    private lateinit var newPasswordEditText: EditText
    private lateinit var confirmNewPasswordEditText: EditText
    private lateinit var otpCodeEditText: EditText
    private lateinit var errorTextView: TextView
    private lateinit var resetPasswordButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_reset_password, container, false)

        newPasswordEditText = view.findViewById(R.id.reset_password_fragment_EditTextTextPassword)
        confirmNewPasswordEditText =
            view.findViewById(R.id.reset_password_fragment_confirm_new_EditTextTextPassword)
        otpCodeEditText = view.findViewById(R.id.reset_password_fragment_otp_editTextNumber)
        errorTextView = view.findViewById(R.id.reset_password_fragment_error_textView)
        resetPasswordButton = view.findViewById(R.id.reset_password_fragment_resetPasswordButton)
        progressBar = view.findViewById(R.id.reset_password_fragment_progressBar)

        resetPasswordButton.setOnClickListener { buttonView: View? ->
            authenticationViewModel.resetPassword(newPasswordEditText.text.toString(),
                confirmNewPasswordEditText.text.toString(), otpCodeEditText.text.toString())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel.getResetPasswordUiState().observe(viewLifecycleOwner) {
                result: ResetPasswordUiState -> updateUI(result.authenticationUiStatus, result.errorMessage)
        }
    }

    private fun updateUI(authenticationUiStatus: AuthenticationUiStatus, errorMessage: String) {
        when (authenticationUiStatus) {
            AuthenticationUiStatus.ResettingPassword, AuthenticationUiStatus.LoggingIn -> {
                progressBar.visibility = View.VISIBLE
                resetPasswordButton.isEnabled = false
            }
            AuthenticationUiStatus.LoginSuccessful ->
                startActivity(Intent(requireActivity(), GalleryActivity::class.java))
            AuthenticationUiStatus.InvalidInput, AuthenticationUiStatus.Failure -> {
                errorTextView.text = errorMessage
                progressBar.visibility = View.GONE
                resetPasswordButton.isEnabled = true
            }
            else -> {
                progressBar.visibility = View.GONE
                progressBar.isEnabled = true
            }
        }
    }
}
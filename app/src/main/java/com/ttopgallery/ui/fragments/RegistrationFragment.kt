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
import com.ttopgallery.ui.states.RegistrationUiState
import com.ttopgallery.ui.viewmodels.fakes.FakeAuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

/** Fragment for user registration. */
@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val authenticationViewModel by activityViewModels<FakeAuthenticationViewModel>()
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var otpCodeEditText: EditText
    private lateinit var errorTextView: TextView
    private lateinit var registerButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_registration, container, false)

        passwordEditText = view.findViewById(R.id.registration_fragment_EditTextTextPassword)
        confirmPasswordEditText =
            view.findViewById(R.id.registration_fragment_confirm_EditTextTextPassword)
        otpCodeEditText = view.findViewById(R.id.registration_fragment_otp_editTextNumber)
        errorTextView = view.findViewById(R.id.registration_fragment_error_textView)
        registerButton = view.findViewById(R.id.registration_fragment_registerButton)
        progressBar = view.findViewById(R.id.registration_fragment_progressBar)

        registerButton.setOnClickListener {
            authenticationViewModel.register(passwordEditText.text.toString(),
                confirmPasswordEditText.text.toString(), otpCodeEditText.text.toString())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel.getRegistrationUiState().observe(viewLifecycleOwner) {
                result: RegistrationUiState -> updateUI(result.authenticationUiStatus, result.errorMessage)
        }
    }

    private fun updateUI(authenticationUiStatus: AuthenticationUiStatus, errorMessage: String) {
        when (authenticationUiStatus) {
            AuthenticationUiStatus.Registering, AuthenticationUiStatus.LoggingIn -> {
                progressBar.visibility = View.VISIBLE
                registerButton.isEnabled = false
            }
            AuthenticationUiStatus.LoginSuccessful ->
                startActivity(Intent(requireActivity(), GalleryActivity::class.java))
            AuthenticationUiStatus.InvalidInput, AuthenticationUiStatus.Failure -> {
                errorTextView.text = errorMessage
                progressBar.visibility = View.GONE
                registerButton.isEnabled = true
            }
            else -> {
                progressBar.visibility = View.GONE
                progressBar.isEnabled = true
            }
        }
    }
}
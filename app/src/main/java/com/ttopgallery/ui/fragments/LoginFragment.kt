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
import com.ttopgallery.localdatasources.entities.OtpType
import com.ttopgallery.ui.activities.GalleryActivity
import com.ttopgallery.ui.states.AuthenticationUiStatus
import com.ttopgallery.ui.states.LoginUiState
import com.ttopgallery.ui.viewmodels.fakes.FakeAuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

/** Fragment for user log n. */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val authenticationViewModel by activityViewModels<FakeAuthenticationViewModel>()
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var errorTextView: TextView
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        emailEditText = view.findViewById(R.id.login_fragment_EditTextTextEmailAddress)
        passwordEditText = view.findViewById(R.id.login_fragment_EditTextTextPassword)
        errorTextView = view.findViewById(R.id.login_fragment_error_textView)
        progressBar = view.findViewById(R.id.login_fragment_progressBar)
        loginButton = view.findViewById(R.id.login_fragment_loginButton)
        val registerLinkTextView: TextView = view.findViewById(R.id.login_fragment_register_link_textView)
        val forgotPasswordLinkTextView: TextView = view.findViewById(R.id.login_fragment_forgot_password_textView)

        loginButton.setOnClickListener {
            authenticationViewModel.login(emailEditText.text.toString(), passwordEditText.text.toString())
        }
        registerLinkTextView.setOnClickListener {
            navigateToGenerateOtpFragment(OtpType.Registration)
        }
        forgotPasswordLinkTextView.setOnClickListener {
            navigateToGenerateOtpFragment(OtpType.ResetPassword)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel.getLoginUiState().observe(viewLifecycleOwner) {
                result: LoginUiState -> updateUI(result.authenticationUiStatus, result.errorMessage)
        }
    }

    private fun updateUI(authenticationUiStatus: AuthenticationUiStatus, errorMessage: String) {
        when (authenticationUiStatus) {
            AuthenticationUiStatus.LoggingIn -> {
                progressBar.visibility = View.VISIBLE
                loginButton.isEnabled = false
            }
            AuthenticationUiStatus.LoginSuccessful ->
                startActivity(Intent(requireActivity(), GalleryActivity::class.java))
            AuthenticationUiStatus.InvalidInput, AuthenticationUiStatus.Failure -> {
                errorTextView.text = errorMessage
                progressBar.visibility = View.GONE
                loginButton.isEnabled = true
            }
            else -> {
                progressBar.visibility = View.GONE
                progressBar.isEnabled = true
            }
        }
    }

    private fun navigateToGenerateOtpFragment(otpType: OtpType) {
        authenticationViewModel.setOtpType(otpType)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.authentication_activity_fragment_container_view,
                GenerateOtpFragment::class.java, null)
            .setReorderingAllowed(true)
            .addToBackStack(null) // name can be null
            .commit()
    }
}
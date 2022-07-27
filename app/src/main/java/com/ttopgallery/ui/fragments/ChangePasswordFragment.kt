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
import com.ttopgallery.ui.states.ChangePasswordUiState
import com.ttopgallery.ui.viewmodels.fakes.FakeAuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

/** Fragment for changing user password. */
@AndroidEntryPoint
class ChangePasswordFragment : Fragment() {

    private val authenticationViewModel by activityViewModels<FakeAuthenticationViewModel>()
    private lateinit var emailEditText: EditText
    private lateinit var currentPasswordEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var confirmNewPasswordEditText: EditText
    private lateinit var errorTextView: TextView
    private lateinit var changePasswordButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_change_password, container, false)

        emailEditText = view.findViewById(R.id.change_password_fragment_EditTextTextEmailAddress)
        currentPasswordEditText =
            view.findViewById(R.id.change_password_fragment_current_EditTextTextPassword)
        newPasswordEditText =
            view.findViewById(R.id.change_password_fragment_new_EditTextTextPassword)
        confirmNewPasswordEditText =
            view.findViewById(R.id.change_password_fragment_confirm_new_EditTextTextPassword)
        errorTextView = view.findViewById(R.id.change_password_fragment_error_textView)
        changePasswordButton = view.findViewById(R.id.change_password_fragment_changePasswordButton)
        progressBar = view.findViewById(R.id.reset_password_fragment_progressBar)

        changePasswordButton.setOnClickListener {
            authenticationViewModel.changePassword(
                emailEditText.text.toString(), currentPasswordEditText.text.toString(),
                newPasswordEditText.text.toString(), confirmNewPasswordEditText.text.toString()
            )
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel.getChangePasswordUiState().observe(viewLifecycleOwner) {
                result: ChangePasswordUiState -> updateUI(result.authenticationUiStatus, result.errorMessage)
        }
    }

    private fun updateUI(authenticationUiStatus: AuthenticationUiStatus, errorMessage: String) {
        when (authenticationUiStatus) {
            AuthenticationUiStatus.ChangingPassword, AuthenticationUiStatus.LoggingIn -> {
                progressBar.visibility = View.VISIBLE
                changePasswordButton.isEnabled = false
            }
            AuthenticationUiStatus.LoginSuccessful -> startActivity(
                Intent(requireActivity(), GalleryActivity::class.java)
            )
            AuthenticationUiStatus.InvalidInput, AuthenticationUiStatus.Failure -> {
                errorTextView.text = errorMessage
                progressBar.visibility = View.GONE
                changePasswordButton.isEnabled = true
            }
            else -> {
                progressBar.visibility = View.GONE
                progressBar.isEnabled = true
            }
        }
    }
}
package com.ttopgallery.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ttopgallery.R
import com.ttopgallery.ui.activities.AuthenticationActivity
import com.ttopgallery.ui.activities.GalleryActivity
import com.ttopgallery.ui.viewmodels.fakes.FakeMainViewModel
import dagger.hilt.android.AndroidEntryPoint

/** Main Fragment. Used for determining authentication state */
@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        progressBar = view.findViewById(R.id.main_fragment_progressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainViewModel by viewModels<FakeMainViewModel>()
        mainViewModel.getMainUiState().observe(viewLifecycleOwner) { result ->
            if (result.isFetchingAuthenticationState) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                updateUI(result.hasActiveLoginToken)
            }
        }
    }

    private fun updateUI(hasActiveLoginToken: Boolean) {
        if (hasActiveLoginToken) {
            startActivity(Intent(requireActivity(), GalleryActivity::class.java))
        } else {
            startActivity(Intent(requireActivity(), AuthenticationActivity::class.java))
        }
    }
}
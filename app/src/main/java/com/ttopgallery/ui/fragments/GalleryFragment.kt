package com.ttopgallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ttopgallery.R
import dagger.hilt.android.AndroidEntryPoint

/** Fragment for gallery home screen. */
@AndroidEntryPoint
class GalleryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_gallery, container, false)

        val imagesButton = view.findViewById<Button>(R.id.gallery_fragment_imagesButton)
        val videosButton = view.findViewById<Button>(R.id.gallery_fragment_videosButton)
        imagesButton.setOnClickListener { navigateToImageListFragment() }
        videosButton.setOnClickListener { navigateToVideoListFragment() }

        return view
    }

    private fun navigateToImageListFragment() {

    }

    private fun navigateToVideoListFragment() {

    }
}
package com.ttopgallery.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttopgallery.R
import com.ttopgallery.ui.fragments.GalleryFragment
import dagger.hilt.android.AndroidEntryPoint

/** Gallery Activity. */
@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.gallery_activity_fragment_container_view)
        if (fragment == null) {
            fragment = GalleryFragment()
            fm.beginTransaction().add(R.id.gallery_activity_fragment_container_view, fragment)
                .commit()
        }
    }
}
package com.ttopgallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttopgallery.ui.fragments.MainFragment
import dagger.hilt.android.AndroidEntryPoint

/** MainActivity class. */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.main_activity_fragment_container_view)

        if (fragment == null) {
            fragment = MainFragment()
            fm.beginTransaction().add(R.id.main_activity_fragment_container_view, fragment).commit()
        }
    }
}
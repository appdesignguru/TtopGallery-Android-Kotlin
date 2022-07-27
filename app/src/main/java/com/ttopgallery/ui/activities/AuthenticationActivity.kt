package com.ttopgallery.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttopgallery.R
import com.ttopgallery.ui.fragments.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

/** Authentication activity. */
@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        val fm = supportFragmentManager

        var fragment = fm.findFragmentById(R.id.authentication_activity_fragment_container_view)
        if (fragment == null) {
            fragment = LoginFragment()
            fm.beginTransaction()
                .add(R.id.authentication_activity_fragment_container_view, fragment).commit()
        }
    }
}
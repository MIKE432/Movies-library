package com.apusart.moviesliblary.ui.logged.logged_activity.main_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apusart.moviesliblary.R
import com.apusart.moviesliblary.ui.logged.profile_activity.ProfileActivity
import kotlinx.android.synthetic.main.main_logged_activity.*

class MainActivity: AppCompatActivity(R.layout.main_logged_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        main_activity_profile_icon_container.setOnClickListener {
            startActivity(Intent(this,  ProfileActivity::class.java))
        }
    }
}
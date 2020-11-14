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

        texttest.setOnClickListener {
            startActivity(Intent(this,  ProfileActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
    }
}
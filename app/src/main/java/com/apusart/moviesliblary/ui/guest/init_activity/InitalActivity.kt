package com.apusart.moviesliblary.ui.guest.init_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.apusart.moviesliblary.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.ui.guest.login_activity.LoginActivity
import com.apusart.moviesliblary.ui.logged.logged_activity.main_activity.MainActivity
import java.lang.Exception

class InitActivity:AppCompatActivity() {
    val viewModel: InitActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MoviesLibraryDatabase.initialize(applicationContext)

        viewModel.isUserLoggedIn.observe(this, Observer {
            startActivity(Intent(this, (
                    if (it)
                        MainActivity::class.java
                    else
                        LoginActivity::class.java
                    ))
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        })
        viewModel.checkIfThereIsUser()
    }

}
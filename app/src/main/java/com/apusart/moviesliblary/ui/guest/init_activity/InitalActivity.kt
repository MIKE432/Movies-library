package com.apusart.moviesliblary.ui.guest.init_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.apusart.MoviesLibraryApplication
import com.apusart.appComponent
import com.apusart.moviesliblary.api.Resource
import com.apusart.moviesliblary.api.local_data_source.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.ui.guest.login_activity.LoginActivity
import com.apusart.moviesliblary.ui.logged.logged_activity.main_activity.MainActivity
import javax.inject.Inject

class InitActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModel: InitActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        MoviesLibraryDatabase.initialize(applicationContext)
        appComponent.inject(this)
        super.onCreate(savedInstanceState)


        viewModel.isUserLoggedIn.observe(this, Observer {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    startActivity(Intent(this, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    )
                    finish()
                }

                Resource.Status.ERROR -> {
                    startActivity(Intent(this, LoginActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    )
                    finish()
                }
            }

        })
    }

}
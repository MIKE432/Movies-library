package com.apusart.moviesliblary.ui.guest.login_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.apusart.MoviesLibraryApplication
import com.apusart.appComponent
import com.apusart.moviesliblary.R
import com.apusart.moviesliblary.api.Resource
import com.apusart.moviesliblary.databinding.LoginActivityBinding
import com.apusart.moviesliblary.api.local_data_source.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.ui.logged.logged_activity.main_activity.MainActivity
import kotlinx.android.synthetic.main.login_activity.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        val binding: LoginActivityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.viewModel = viewModel

        MoviesLibraryDatabase.initialize(this)

        login_activity_process_login.setOnClickListener {
            login_activity_process_container.transitionToEnd()
            viewModel.logIn()
            Toast.makeText(this, "${viewModel.email.value}", Toast.LENGTH_LONG).show()
        }

        viewModel.sessionIdResource.observe(this, Observer {

            when(it.status) {
                Resource.Status.SUCCESS -> {

                    startActivity(Intent(this@LoginActivity, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                    finish()
                }
                Resource.Status.PENDING -> {

                    login_activity_process_container.transitionToEnd()
                }
                Resource.Status.ERROR -> {

                    login_activity_password_text.text.clear()
                    login_activity_process_container.transitionToStart()
                    login_activity_error.isVisible = true
                }
            }


        })

    }
}
package com.apusart.moviesliblary.ui.guest.login_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.apusart.moviesliblary.R
import com.apusart.moviesliblary.databinding.LoginActivityBinding
import com.apusart.moviesliblary.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.ui.logged.logged_activity.main_activity.MainActivity
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: LoginActivityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.viewModel = viewModel

        MoviesLibraryDatabase.initialize(this)

        login_activity_process_login.setOnClickListener {
            login_activity_process_container.transitionToEnd()
            viewModel.logIn()
            Toast.makeText(this, "${viewModel.email.value}", Toast.LENGTH_LONG).show()
        }

        viewModel.inProgress.observe(this, Observer { inProgress ->

            login_activity_error.isVisible = false

            if(inProgress) {
                login_activity_process_container.transitionToEnd()
            } else {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        })

        viewModel.isError.observe(this, Observer { isError ->
            if(isError) {
                login_activity_password_text.text.clear()
                login_activity_process_container.transitionToStart()
                login_activity_error.isVisible = true
            }
        })
    }
}
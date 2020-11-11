package com.apusart.moviesliblary.ui.guest.login_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.apusart.moviesliblary.R
import com.apusart.moviesliblary.databinding.LoginActivityBinding
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: LoginActivityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.viewModel = viewModel

        login_activity_process_login.setOnClickListener {
            login_activity_process_container.transitionToEnd()
            viewModel.logIn()
            Toast.makeText(this, "${viewModel.email.value}", Toast.LENGTH_LONG).show()
        }

        login_activity_sign_in.setOnClickListener {
            login_activity_process_container.transitionToStart()
            Toast.makeText(this, "${viewModel.email.value}", Toast.LENGTH_LONG).show()
        }
    }
}
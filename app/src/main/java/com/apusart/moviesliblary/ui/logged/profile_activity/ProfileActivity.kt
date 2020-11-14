package com.apusart.moviesliblary.ui.logged.profile_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.apusart.moviesliblary.R
import com.apusart.moviesliblary.databinding.LoginActivityBinding
import com.apusart.moviesliblary.databinding.ProfileActivityBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_activity.*

class ProfileActivity: AppCompatActivity() {
    private val viewModel: ProfileActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ProfileActivityBinding = DataBindingUtil.setContentView(this, R.layout.profile_activity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        Glide.with(this)
            .load("https://pbs.twimg.com/profile_images/749651633093611522/Hh1Q9-ro.jpg")
            .circleCrop()
            .into(profile_activity_profile_photo)
        profile_activity_back_icon.setOnClickListener {
            onBackPressed()
        }
        viewModel.getProfileInfo()
    }
}
package com.apusart.moviesliblary.ui.logged.profile_activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.apusart.MoviesLibraryApplication
import com.apusart.moviesliblary.R
import com.apusart.moviesliblary.api.Resource
import com.apusart.moviesliblary.databinding.LoginActivityBinding
import com.apusart.moviesliblary.databinding.ProfileActivityBinding
import com.apusart.moviesliblary.tools.CreatedListAdapter
import com.apusart.moviesliblary.ui.guest.init_activity.InitActivity
import com.apusart.moviesliblary.ui.logged.logged_activity.main_activity.MainActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.profile_activity.*
import javax.inject.Inject

class ProfileActivity: AppCompatActivity() {
    @Inject
    lateinit var viewModel: ProfileActivityViewModel
    private lateinit var createdListAdapter: CreatedListAdapter

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MoviesLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        val binding: ProfileActivityBinding = DataBindingUtil.setContentView(this, R.layout.profile_activity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        createdListAdapter = CreatedListAdapter()

        profile_activity_created_list.apply {
            adapter = createdListAdapter
        }

        viewModel.createdList.observe(this, {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    createdListAdapter.submitList(it.data!!.results)
                }
                Resource.Status.PENDING -> {


                }
                Resource.Status.ERROR -> {

                }
            }
        })

        val alertDialog = AlertDialog.Builder(this)

        alertDialog
            .setTitle("Wyloguj")
            .setMessage("Czy na pewno chcesz się wylogować?")
            .setPositiveButton(R.string.logout) { _, _ ->
                viewModel.logout()
            }
            .setNegativeButton(R.string.abort) { dialog, _ ->
                dialog.cancel()
            }


        Glide
            .with(this)
            .load("https://pbs.twimg.com/profile_images/749651633093611522/Hh1Q9-ro.jpg")
            .circleCrop()
            .into(profile_activity_profile_photo)


        profile_activity_back_icon.setOnClickListener {
            onBackPressed()
        }

        profile_activity_logout.setOnClickListener {
            alertDialog.show()
        }

        viewModel.loggedOut.observe(this, Observer {

            when(it.status) {
                Resource.Status.SUCCESS -> {
                    startActivity(Intent(this, InitActivity::class.java)
                        .addFlags(
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                    Intent.FLAG_ACTIVITY_NEW_TASK or
                                    Intent.FLAG_ACTIVITY_NO_ANIMATION
                        ))
                    ActivityCompat.finishAffinity(this)
                }
                Resource.Status.PENDING -> {


                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this, "Nie udało się wylogować", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}

package com.apusart.moviesliblary.ui.guest.init_activity


import androidx.lifecycle.ViewModel

import com.apusart.moviesliblary.api.repositories.UserRepository

import javax.inject.Inject

class InitActivityViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {
    val isUserLoggedIn = repository.getUserDetails(1)
}
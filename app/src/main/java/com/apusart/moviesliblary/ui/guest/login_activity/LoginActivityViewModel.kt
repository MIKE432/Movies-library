package com.apusart.moviesliblary.ui.guest.login_activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.moviesliblary.api.Resource
import com.apusart.moviesliblary.api.local_data_source.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.api.local_data_source.db.User
import com.apusart.moviesliblary.api.repositories.UserRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val sessionIdResource = MutableLiveData<Resource<String>>()

    fun logIn() {
        viewModelScope.launch {
            try {
                sessionIdResource.value = Resource.pending()

                if (email.value != null && password.value != null)
                    sessionIdResource.value = repository.logIn(email.value!!, password.value!!)

            } catch (e: Exception) {
                sessionIdResource.value = Resource.error(e.message)
                e.printStackTrace()
            }
        }
    }
}
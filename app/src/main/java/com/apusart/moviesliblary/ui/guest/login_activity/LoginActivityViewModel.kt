package com.apusart.moviesliblary.ui.guest.login_activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.moviesliblary.api.MovieLibraryService
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginActivityViewModel: ViewModel() {
    private val service = MovieLibraryService()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()


    fun logIn() {
        viewModelScope.launch {
            try {
                var sessionId = ""

                if (email.value != null && password.value != null)
                    sessionId = service.createSession(email.value!!, password.value!!)

                Log.e("session", sessionId)

            } catch (e: Exception) {
                Log.e("err", e.message)
                e.printStackTrace()
            }
        }
    }
}
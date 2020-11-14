package com.apusart.moviesliblary.ui.guest.login_activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.moviesliblary.api.MovieLibraryService
import com.apusart.moviesliblary.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.db.User
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginActivityViewModel: ViewModel() {
    private val service = MovieLibraryService()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isError = MutableLiveData<Boolean>()
    val inProgress = MutableLiveData<Boolean>()

    fun logIn() {
        viewModelScope.launch {
            try {
                inProgress.value = true
                var sessionId = ""

                if (email.value != null && password.value != null)
                    sessionId = service.createSession(email.value!!, password.value!!)

                val user = service.getUserDetails(sessionId)

                MoviesLibraryDatabase.db.userDao().insertUser(User(1, user.name!!, user.username!!, sessionId))

                inProgress.value = false
            } catch (e: Exception) {
                isError.value = true
                e.printStackTrace()
            }
        }
    }
}
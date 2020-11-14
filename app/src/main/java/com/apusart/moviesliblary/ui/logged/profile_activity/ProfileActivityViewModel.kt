package com.apusart.moviesliblary.ui.logged.profile_activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.moviesliblary.api.MovieLibraryService
import com.apusart.moviesliblary.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.db.User
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileActivityViewModel: ViewModel() {
    private val service = MovieLibraryService()
    val user =  MutableLiveData<User>()
    val userName = MutableLiveData("XD")

    fun getProfileInfo() {
        viewModelScope.launch {
            try {
                val users = MoviesLibraryDatabase.db.userDao().getUser()
                if (users.isNotEmpty()) {
                    user.value = users[0]
                    userName.value = users[0].username
                    val userFromResponse = service.getUserDetails(users[0].sessionId)
                    user.value = User(1, userFromResponse.name!!, userFromResponse.username!!, users[0].sessionId)
                    userName.value = userFromResponse.username

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
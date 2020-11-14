package com.apusart.moviesliblary.ui.guest.init_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.moviesliblary.db.MoviesLibraryDatabase
import kotlinx.coroutines.launch

class InitActivityViewModel: ViewModel() {
    val isUserLoggedIn = MutableLiveData<Boolean>()

    fun checkIfThereIsUser() {
        viewModelScope.launch {
            try {
                val users = MoviesLibraryDatabase.db.userDao().getUser()
                isUserLoggedIn.value = users.isNotEmpty()
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
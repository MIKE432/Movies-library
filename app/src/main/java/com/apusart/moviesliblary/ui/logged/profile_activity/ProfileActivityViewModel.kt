package com.apusart.moviesliblary.ui.logged.profile_activity

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apusart.moviesliblary.api.CreatedList
import com.apusart.moviesliblary.api.Resource
import com.apusart.moviesliblary.api.local_data_source.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.api.local_data_source.db.User
import com.apusart.moviesliblary.api.repositories.UserRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ProfileActivityViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {

    val user =  repository.getUserDetails(1)
    val loggedOut = MutableLiveData<Resource<Boolean>>()
    val createdList = repository.getCreatedLists(1, 1, "pl")

    fun logout() {
        viewModelScope.launch {
            try {
                loggedOut.value = Resource.pending()
                if (user.value?.data?.sessionId == null) {
                    loggedOut.value = Resource.error("cannot logout user")
                } else {
                    loggedOut.value = repository.deleteSession(user.value!!.data!!.sessionId)
                }

            } catch (e: Exception) {
                loggedOut.value = Resource.error("cannot logout user")
                e.printStackTrace()
            }
        }
    }
}

@BindingAdapter("userResourceName")
fun setText(textView: TextView, res: Resource<User>?) {
    if (res == null)
        return
    if(res.status == Resource.Status.SUCCESS)
        textView.text = res.data!!.username
}
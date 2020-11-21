package com.apusart.moviesliblary.api.local_data_source

import androidx.lifecycle.LiveData
import com.apusart.moviesliblary.api.Resource
import com.apusart.moviesliblary.api.UserDetailsResponse
import com.apusart.moviesliblary.api.local_data_source.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.api.local_data_source.db.User
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val db: MoviesLibraryDatabase) {

    suspend fun saveUser(user: User) {
        db.userDao().save(user)
    }

    suspend fun getUser(id: Int): Resource<User> {
        val user = db.userDao().getUser(id)
        return if (user == null)
            Resource.error("there is no such user with given ID")
        else
            Resource.success(user)
    }

    suspend fun removeUser(id: Int): Resource<Boolean> {
        return if (db.userDao().deleteUser(id) == 0)
            Resource.error("cannot removeUser with given ID")
        else
            Resource.success(true)
    }
}
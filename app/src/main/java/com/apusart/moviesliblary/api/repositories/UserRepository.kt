package com.apusart.moviesliblary.api.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.apusart.moviesliblary.api.*
import com.apusart.moviesliblary.api.local_data_source.UserLocalDataSource
import com.apusart.moviesliblary.api.local_data_source.db.User
import com.apusart.moviesliblary.api.remote_data_source.UserRemoteDataSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend fun logIn(username: String, password: String): Resource<String> {
        val sessionResource = userRemoteDataSource.createSession(username, password)

        if (sessionResource.status == Resource.Status.SUCCESS) {
            val user = userRemoteDataSource.getUserDetails(sessionResource.data!!)
            userLocalDataSource.saveUser(
                User(
                1,
                    user.data!!.name!!,
                    user.data.username!!,
                    sessionResource.data,
                    user.data.id!!
            ))
        }

        return sessionResource
    }

    fun getUserDetails(userId: Int): LiveData<Resource<User>> {

        return liveData {
            val user = userLocalDataSource.getUser(userId)
            if (user.status == Resource.Status.SUCCESS) {
                emitSource(
                    performGet(
                        dbQuery = { user },
                        apiCall = { userRemoteDataSource.getUserDetails(user.data!!.sessionId) },
                        saveResult = { userLocalDataSource.saveUser(User(1, it.name!!, it.username!!, user.data!!.sessionId, it.id!!)) }
                    )
                )
            } else
                emit(Resource.error("There is no logged user", null))

        }
    }

    suspend fun deleteSession(sessionId: String): Resource<Boolean> {
        val deleteSessionResource = userRemoteDataSource.deleteSession(sessionId)
        userLocalDataSource.removeUser(1)
        return if (deleteSessionResource.status == Resource.Status.SUCCESS)
            Resource.success(true)
        else Resource.error("Cannot logout user")
    }

    fun getCreatedLists(userId: Int, page: Int, language: String): LiveData<Resource<CreatedListResponse>> {

        return liveData {
            val user = userLocalDataSource.getUser(userId)
            if (user.status == Resource.Status.SUCCESS) {
                emit(userRemoteDataSource.getCreatedLists(user.data!!.sessionId, user.data.id, page, language))
            } else
                emit(Resource.error("There is no logged user", null))

        }
    }
}
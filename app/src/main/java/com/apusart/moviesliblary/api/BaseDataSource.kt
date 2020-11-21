package com.apusart.moviesliblary.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()

            if(response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }

            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.e("remoteDataSource", message)
        return Resource.error("Network has failed for a following reason: $message")
    }
}


fun<T, A> performGet(dbQuery: suspend () -> Resource<T>,
                     apiCall: suspend () -> Resource<A>,
                     saveResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData {
        emit(Resource.pending())

        val localSource = dbQuery.invoke()
        emit(localSource)

        val remoteSource = apiCall.invoke()
        if (remoteSource.status == Resource.Status.SUCCESS)
            saveResult(remoteSource.data!!)
        else if (remoteSource.status == Resource.Status.ERROR) {
            emit(Resource.error(remoteSource.message))
            emit(localSource)
        }
    }
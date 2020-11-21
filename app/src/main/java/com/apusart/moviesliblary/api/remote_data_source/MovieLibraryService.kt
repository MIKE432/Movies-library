package com.apusart.moviesliblary.api.remote_data_source

import com.apusart.moviesliblary.api.*
import com.apusart.moviesliblary.tools.Defaults
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val service: IMovieLibraryService
): BaseDataSource() {

    private suspend fun getNewRequestToken() = getResult { service.getNewRequestToken() }

    private suspend fun authorizeTokenByLogin(username: String, password: String): Resource<String> {
        val tokenResponse = getNewRequestToken()

        return if (tokenResponse.status == Resource.Status.SUCCESS && tokenResponse.data?.success == true) {
            val body = AuthBody(username, password, tokenResponse.data.request_token)
            val authorizedToken = getResult { service.authTokenWithLogin(body) }

            if (authorizedToken.status == Resource.Status.SUCCESS && authorizedToken.data?.success == true) {
                Resource.success(authorizedToken.data.request_token)
            } else {
                Resource.error(authorizedToken.message)
            }
        } else {
            Resource.error(tokenResponse.message)
        }
    }

    suspend fun createSession(username: String, password: String): Resource<String> {
        val authorizedToken = authorizeTokenByLogin(username, password)

        return if (authorizedToken.status == Resource.Status.SUCCESS) {
            val sessionResponse =
                getResult { service.createNewSession(SessionBody(authorizedToken.data!!)) }
            if (sessionResponse.status == Resource.Status.SUCCESS) {
                Resource.success(sessionResponse.data?.session_id!!)
            } else {
                Resource.error(sessionResponse.message)
            }
        } else {
            Resource.error(authorizedToken.message)
        }
    }

    suspend fun getUserDetails(sessionId: String) = getResult { service.getUserDetails(sessionId) }

    suspend fun deleteSession(sessionId: String) = getResult { service.deleteSession(
        DeleteSessionBody(sessionId)
    ) }

    suspend fun getCreatedLists(sessionId: String, userId: Int, page: Int, language: String) = getResult { service.getCreatedLists(userId, sessionId, page, language) }
}
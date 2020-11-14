package com.apusart.moviesliblary.api

import android.util.Log
import com.apusart.moviesliblary.tools.Defaults
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieLibraryService {
    private val service: IMovieLibraryService

    init {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =  HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Defaults.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(IMovieLibraryService::class.java)
    }

    private suspend fun getNewRequestToken(): NewTokenResponse? {

        val tokenResponse = service.getNewRequestToken()
        tokenResponse?.success ?: throw Exception()

        return tokenResponse
    }


    private suspend fun authorizeTokenByLogin(username: String, password: String): String {

        val tokenResponse = getNewRequestToken()?.request_token ?: throw Exception()
        val body = AuthBody(username, password, tokenResponse)

        val authorizedToken = service.authTokenWithLogin(body)
        authorizedToken?.success ?: throw Exception()

        return authorizedToken.request_token
    }

    suspend fun createSession(username: String, password: String): String {

        val authorizedToken = authorizeTokenByLogin(username, password)

        val sessionResponse = service.createNewSession(SessionBody(authorizedToken))

        sessionResponse?.success ?: throw Exception()

        return sessionResponse.session_id
    }

    suspend fun getUserDetails(sessionId: String): UserDetailsResponse {
        val userDetails = service.getUserDetails(sessionId = sessionId)

        userDetails?.username ?: throw Exception()

        return userDetails
    }
}
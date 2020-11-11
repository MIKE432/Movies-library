package com.apusart.moviesliblary.api

import com.apusart.moviesliblary.tools.Defaults
import retrofit2.http.*

interface IMovieLibraryService {

    @GET("authentication/token/new")
    suspend fun getNewRequestToken(@Query("api_key") api_key: String = Defaults.apiKey): NewTokenResponse?

    @POST("authentication/token/validate_with_login")
    suspend fun authTokenWithLogin(@Body authBody: AuthBody, @Query("api_key") api_key: String = Defaults.apiKey): NewTokenResponse?

    @POST("authentication/session/new")
    suspend fun createNewSession(@Body sessionBody: SessionBody, @Query("api_key") api_key: String = Defaults.apiKey): SessionResponse?
}
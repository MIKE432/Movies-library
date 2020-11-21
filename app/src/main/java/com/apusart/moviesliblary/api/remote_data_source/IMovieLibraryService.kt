package com.apusart.moviesliblary.api.remote_data_source

import com.apusart.moviesliblary.api.*
import com.apusart.moviesliblary.tools.Defaults
import retrofit2.Response
import retrofit2.http.*

interface IMovieLibraryService {

    @GET("authentication/token/new")
    suspend fun getNewRequestToken(@Query("api_key") apiKey: String = Defaults.apiKey): Response<NewTokenResponse>

    @POST("authentication/token/validate_with_login")
    suspend fun authTokenWithLogin(@Body authBody: AuthBody, @Query("api_key") apiKey: String = Defaults.apiKey): Response<NewTokenResponse>

    @POST("authentication/session/new")
    suspend fun createNewSession(@Body sessionBody: SessionBody, @Query("api_key") apiKey: String = Defaults.apiKey): Response<SessionResponse>

    @GET("account")
    suspend fun getUserDetails(@Query("session_id") sessionId: String, @Query("api_key") apiKey: String = Defaults.apiKey): Response<UserDetailsResponse>

    @GET("account/{account_id}/lists")
    suspend fun getCreatedLists(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "pl",
        @Query("api_key") apiKey: String = Defaults.apiKey
    ): Response<CreatedListResponse>

    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    suspend fun deleteSession(@Body deleteSessionBody: DeleteSessionBody, @Query("api_key") apiKey: String = Defaults.apiKey): Response<DeleteSessionResponse>
}
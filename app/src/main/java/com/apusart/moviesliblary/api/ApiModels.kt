package com.apusart.moviesliblary.api


data class NewTokenResponse (
    val success: Boolean,
    val request_token: String
)

data class AuthBody (
    val username: String,
    val password: String,
    val request_token: String
)

data class SessionBody (
    val request_token: String
)

data class SessionResponse (
    val success: Boolean,
    val session_id: String
)

data class UserDetailsResponse (
    val status_code: Int?,
    val status_message: String?,
    val id: Int?,
    val name: String?,
    val username: String?

)

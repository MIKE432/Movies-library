package com.apusart.moviesliblary.api

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS,
        ERROR,
        PENDING
    }

    companion object {
        fun<T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun<T> error(message: String?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun<T> pending(data: T? = null): Resource<T> {
            return Resource(Status.PENDING, data, null)
        }
    }
}

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

data class DeleteSessionBody (
    val session_id: String
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

class CreatedListResponse(
    val status_code: Int?,
    val status_message: String?,
    val results: List<CreatedList>?,
    val total_pages: Int?,
    val page: Int?,
    val total_results: Int?
)

data class CreatedList(
    val description: String,
    val favourite_count: Int,
    val id: Int,
    val item_count: Int,
    val list_type: String,
    val name: String,
    val poster_path: String
)

data class DeleteSessionResponse(
    val success: Boolean,
    val status_message: String?,
    val status_code: Int?
)

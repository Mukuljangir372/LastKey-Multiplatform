package com.mu.lastkey.feature.login.data.network.api

interface LoginApi {
    data class Request(
        val email: String,
        val password: String
    )

    data class Response(
        val loggedInUserId: String
    )

    suspend fun signIn(request: Request): Response
    suspend fun signUp(request: Request)
    suspend fun isLoggedIn(): Boolean
}

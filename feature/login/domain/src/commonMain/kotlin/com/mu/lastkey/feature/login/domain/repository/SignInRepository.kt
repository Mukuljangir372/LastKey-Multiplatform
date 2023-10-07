package com.mu.lastkey.feature.login.domain.repository

import com.mu.lastkey.core.domain.model.user.User

interface SignInRepository {
    suspend fun signIn(username: String, password: String): User
}

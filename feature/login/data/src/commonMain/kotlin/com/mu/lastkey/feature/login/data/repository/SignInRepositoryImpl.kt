package com.mu.lastkey.feature.login.data.repository

import com.mu.lastkey.core.domain.model.coroutine.AppCoroutineDispatchers
import com.mu.lastkey.core.domain.model.user.User
import com.mu.lastkey.feature.login.domain.repository.SignInRepository

class SignInRepositoryImpl(
    private val dispatchers: AppCoroutineDispatchers
) : SignInRepository {
    override suspend fun signIn(
        username: String,
        password: String
    ): User {
        return User(id = "", username = "")
    }
}

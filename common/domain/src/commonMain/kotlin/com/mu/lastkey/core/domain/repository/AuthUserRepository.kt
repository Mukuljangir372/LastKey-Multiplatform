package com.mu.lastkey.core.domain.repository

import com.mu.lastkey.core.domain.model.user.AuthSession

interface AuthUserRepository {
    suspend fun getActiveAuthSession(): AuthSession?
}

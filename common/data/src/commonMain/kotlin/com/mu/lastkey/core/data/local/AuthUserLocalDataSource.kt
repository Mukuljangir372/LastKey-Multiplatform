package com.mu.lastkey.core.data.local

import com.mu.lastkey.core.data.local.model.AuthSessionLocalModel
import com.mu.lastkey.core.data.local.model.AuthUserLocalModel

interface AuthUserLocalDataSource {
    suspend fun getLoggedInAuthUser(): AuthUserLocalModel?
    suspend fun getAuthUser(id: String): AuthUserLocalModel?
    suspend fun getActiveAuthSession(): AuthSessionLocalModel?
    suspend fun insertAuthUser(model: AuthUserLocalModel)
    suspend fun insertAuthSession(model: AuthSessionLocalModel)
}

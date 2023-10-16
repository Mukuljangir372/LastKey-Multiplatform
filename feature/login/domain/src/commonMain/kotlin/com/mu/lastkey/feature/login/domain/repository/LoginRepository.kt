package com.mu.lastkey.feature.login.domain.repository

import com.mu.lastkey.core.domain.model.wrapper.ResultWrapper
import com.mu.lastkey.feature.login.domain.model.LoginRequest
import com.mu.lastkey.feature.login.domain.model.LoginResponse

interface LoginRepository {
    suspend fun signIn(request: LoginRequest): ResultWrapper<LoginResponse>
    suspend fun signUp(request: LoginRequest): ResultWrapper<LoginResponse>
}

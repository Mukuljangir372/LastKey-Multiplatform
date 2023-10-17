package com.mu.lastkey.feature.login.domain.repository

import com.mu.lastkey.core.domain.model.wrapper.ResultWrapper
import com.mu.lastkey.feature.login.domain.model.SignInRequest
import com.mu.lastkey.feature.login.domain.model.SignInResponse
import com.mu.lastkey.feature.login.domain.model.SignUpRequest
import com.mu.lastkey.feature.login.domain.model.SignUpResponse

interface LoginRepository {
    suspend fun signIn(request: SignInRequest): ResultWrapper<SignInResponse>
    suspend fun signUp(request: SignUpRequest): ResultWrapper<SignUpResponse>
}

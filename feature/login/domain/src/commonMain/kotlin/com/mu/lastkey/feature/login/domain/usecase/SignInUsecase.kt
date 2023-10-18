package com.mu.lastkey.feature.login.domain.usecase

import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.domain.model.user.User

interface SignInUsecase {
    data class Params(val email: String, val password: String)
    data class Result(val user: User)
    suspend operator fun invoke(params: Params): ResultWrapper<Result?>
}

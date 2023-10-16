package com.mu.lastkey.feature.login.domain.usecase

import com.mu.lastkey.core.domain.model.user.User
import com.mu.lastkey.core.domain.model.wrapper.ResultWrapper

interface SignUpUsecase {
    data class Params(val username: String, val password: String)
    data class Result(val user: User)
    operator fun invoke(params: Params): ResultWrapper<Result>
}
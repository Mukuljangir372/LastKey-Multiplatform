package com.mu.lastkey.feature.login.domain.usecase

import com.mu.lastkey.core.domain.model.ResultWrapper

interface SignUpUsecase {
    data class Params(val email: String, val password: String)
    class Result
    suspend operator fun invoke(params: Params): ResultWrapper<Result?>
}

package com.mu.lastkey.feature.login.data.usecase

import com.mu.lastkey.core.domain.model.wrapper.ResultWrapper
import com.mu.lastkey.feature.login.domain.model.SignUpRequest
import com.mu.lastkey.feature.login.domain.repository.LoginRepository
import com.mu.lastkey.feature.login.domain.usecase.SignUpUsecase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SignUpUsecaseImpl(
    private val repository: LoginRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : SignUpUsecase {
    override suspend fun invoke(
        params: SignUpUsecase.Params
    ): ResultWrapper<SignUpUsecase.Result?> {
        return withContext(coroutineDispatcher) {
            signUp(
                params = params,
                repository = repository
            )
        }
    }

    companion object {
        private suspend fun signUp(
            params: SignUpUsecase.Params,
            repository: LoginRepository
        ): ResultWrapper<SignUpUsecase.Result?> {
            val request = mapParamsToLoginRequest(params)
            val signUpResult = repository.signUp(request)
            return signUpResult.map { SignUpUsecase.Result() }
        }

        private fun mapParamsToLoginRequest(params: SignUpUsecase.Params): SignUpRequest {
            return SignUpRequest(
                email = params.email,
                password = params.password
            )
        }
    }
}

package com.mu.lastkey.feature.login.data.usecase

import com.mu.lastkey.core.domain.model.wrapper.ResultWrapper
import com.mu.lastkey.feature.login.domain.model.SignInRequest
import com.mu.lastkey.feature.login.domain.repository.LoginRepository
import com.mu.lastkey.feature.login.domain.usecase.SignInUsecase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SignInUsecaseImpl(
    private val repository: LoginRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : SignInUsecase {
    override suspend fun invoke(
        params: SignInUsecase.Params
    ): ResultWrapper<SignInUsecase.Result?> {
        return withContext(coroutineDispatcher) {
            signIn(
                params = params,
                repository = repository
            )
        }
    }

    companion object {
        private suspend fun signIn(
            params: SignInUsecase.Params,
            repository: LoginRepository
        ): ResultWrapper<SignInUsecase.Result?> {
            val request = mapParamsToSignInRequest(params)
            val result = repository.signIn(request)
            return result.map {
                if (result.data?.user != null) {
                    SignInUsecase.Result(user = result.data!!.user)
                } else {
                    null
                }
            }
        }

        private fun mapParamsToSignInRequest(model: SignInUsecase.Params): SignInRequest {
            return SignInRequest(email = model.email, password = model.password)
        }
    }
}

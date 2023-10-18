package com.mu.lastkey.feature.login.data.usecase

import com.mu.lastkey.core.domain.InputValidator
import com.mu.lastkey.core.domain.model.AppStrings
import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.feature.login.domain.model.SignInRequest
import com.mu.lastkey.feature.login.domain.repository.LoginRepository
import com.mu.lastkey.feature.login.domain.usecase.SignInUsecase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SignInUsecaseImpl(
    private val repository: LoginRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val strings: AppStrings,
    private val inputValidator: InputValidator
) : SignInUsecase {
    override suspend fun invoke(
        params: SignInUsecase.Params
    ): ResultWrapper<SignInUsecase.Result?> {
        return withContext(coroutineDispatcher) {
            signIn(
                params = params,
                repository = repository,
                strings = strings,
                inputValidator = inputValidator
            )
        }
    }

    companion object {
        private suspend fun signIn(
            params: SignInUsecase.Params,
            repository: LoginRepository,
            strings: AppStrings,
            inputValidator: InputValidator
        ): ResultWrapper<SignInUsecase.Result?> {
            val validationResult = validateParamsAsResult(params, inputValidator, strings)
            if (validationResult != null) return validationResult

            val request = mapParamsToSignInRequest(params)
            val result = repository.signIn(request)
            val success = result.isSuccess()
            return if (success && result.data?.user != null) {
                ResultWrapper.Success(
                    data = SignInUsecase.Result(user = result.data!!.user),
                    message = strings.signedInSuccess
                )
            } else {
                ResultWrapper.Failure(message = result.message)
            }
        }

        private fun mapParamsToSignInRequest(
            model: SignInUsecase.Params
        ): SignInRequest {
            return SignInRequest(
                email = model.email,
                password = model.password
            )
        }

        private fun validateParamsAsResult(
            params: SignInUsecase.Params,
            inputValidator: InputValidator,
            strings: AppStrings
        ): ResultWrapper<SignInUsecase.Result?>? {
            val validEmail = inputValidator.isValidEmail(params.email)
            val validPassword = inputValidator.isValidPassword(params.password)
            val message = if (!validEmail) strings.enterValidEmail
            else if (!validPassword) strings.enterValidPassword
            else ""
            return if (message.isNotBlank()) ResultWrapper.Failure(message = message) else null
        }
    }
}

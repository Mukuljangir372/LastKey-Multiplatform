package com.mu.lastkey.feature.login.data.usecase

import com.mu.lastkey.core.domain.InputValidator
import com.mu.lastkey.core.domain.model.AppStrings
import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.feature.login.domain.model.SignUpRequest
import com.mu.lastkey.feature.login.domain.repository.LoginRepository
import com.mu.lastkey.feature.login.domain.usecase.SignInUsecase
import com.mu.lastkey.feature.login.domain.usecase.SignUpUsecase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SignUpUsecaseImpl(
    private val repository: LoginRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val strings: AppStrings,
    private val inputValidator: InputValidator
) : SignUpUsecase {
    override suspend fun invoke(
        params: SignUpUsecase.Params
    ): ResultWrapper<SignUpUsecase.Result?> {
        return withContext(coroutineDispatcher) {
            signUp(
                params = params,
                repository = repository,
                strings = strings,
                inputValidator = inputValidator
            )
        }
    }

    companion object {
        private suspend fun signUp(
            params: SignUpUsecase.Params,
            repository: LoginRepository,
            strings: AppStrings,
            inputValidator: InputValidator
        ): ResultWrapper<SignUpUsecase.Result?> {
            val validationResult = validateParamsAsResult(params, inputValidator, strings)
            if (validationResult != null) return validationResult

            val request = mapParamsToLoginRequest(params)
            val result = repository.signUp(request)
            val success = result.isSuccess()
            return if (success && result.data?.loggedInUserId != null) {
                ResultWrapper.Success(
                    data = SignUpUsecase.Result(),
                    message = strings.signedUpSuccess
                )
            } else {
                ResultWrapper.Failure(message = result.message)
            }
        }

        private fun mapParamsToLoginRequest(
            params: SignUpUsecase.Params
        ): SignUpRequest {
            return SignUpRequest(
                email = params.email,
                password = params.password
            )
        }

        private fun validateParamsAsResult(
            params: SignUpUsecase.Params,
            inputValidator: InputValidator,
            strings: AppStrings
        ): ResultWrapper<SignUpUsecase.Result?>? {
            val validEmail = inputValidator.isValidEmail(params.email)
            val validPassword = inputValidator.isValidPassword(params.password)
            val message = if (!validEmail) {
                strings.enterValidEmail
            } else if (!validPassword) {
                strings.enterValidPassword
            } else {
                ""
            }
            return if (message.isNotBlank()) {
                ResultWrapper.Failure(message = message)
            } else null
        }
    }
}

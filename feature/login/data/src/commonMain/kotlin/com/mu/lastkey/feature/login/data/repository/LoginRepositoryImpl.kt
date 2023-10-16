package com.mu.lastkey.feature.login.data.repository

import com.mu.lastkey.core.domain.model.wrapper.ResultWrapper
import com.mu.lastkey.feature.login.data.network.LoginNetworkDataSource
import com.mu.lastkey.feature.login.data.network.api.LoginApi
import com.mu.lastkey.feature.login.domain.model.LoginRequest
import com.mu.lastkey.feature.login.domain.model.LoginResponse
import com.mu.lastkey.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(
    private val networkSource: LoginNetworkDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : LoginRepository {
    override suspend fun signIn(request: LoginRequest): ResultWrapper<LoginResponse> {
        return withContext(coroutineDispatcher) {
            signIn(
                request = request,
                networkSource = networkSource
            )
        }
    }

    override suspend fun signUp(request: LoginRequest): ResultWrapper<LoginResponse> {
        return withContext(coroutineDispatcher) {
            signUp(
                request = request,
                networkSource = networkSource
            )
        }
    }

    companion object {
        private suspend fun signIn(
            request: LoginRequest,
            networkSource: LoginNetworkDataSource
        ): ResultWrapper<LoginResponse> {
            return try {
                val signInRequest = LoginApi.Request(
                    email = request.email,
                    password = request.password
                )
                val signInResponse = networkSource.signIn(signInRequest)
                val successData = LoginResponse(loggedInUserId = signInResponse.loggedInUserId)
                ResultWrapper.Success(successData)
            } catch (e: Exception) {
                ResultWrapper.Failure(message = e.message.orEmpty())
            }
        }

        private suspend fun signUp(
            request: LoginRequest,
            networkSource: LoginNetworkDataSource
        ): ResultWrapper<LoginResponse> {
            return try {
                val signInRequest = LoginApi.Request(
                    email = request.email,
                    password = request.password
                )
                val signInResponse = networkSource.signUp(signInRequest)
                val successData = LoginResponse(loggedInUserId = signInResponse.loggedInUserId)
                ResultWrapper.Success(successData)
            } catch (e: Exception) {
                ResultWrapper.Failure(message = e.message.orEmpty())
            }
        }
    }
}
package com.mu.lastkey.feature.login.data.repository

import com.mu.lastkey.core.data.local.AuthUserLocalDataSource
import com.mu.lastkey.core.data.local.model.AuthSessionLocalModel
import com.mu.lastkey.core.data.local.model.AuthUserLocalModel
import com.mu.lastkey.core.data.mapper.AuthUserMapper
import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.domain.model.user.User
import com.mu.lastkey.feature.login.data.network.LoginNetworkDataSource
import com.mu.lastkey.feature.login.data.network.api.LoginApi
import com.mu.lastkey.feature.login.domain.model.SignInRequest
import com.mu.lastkey.feature.login.domain.model.SignInResponse
import com.mu.lastkey.feature.login.domain.model.SignUpRequest
import com.mu.lastkey.feature.login.domain.model.SignUpResponse
import com.mu.lastkey.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(
    private val networkSource: LoginNetworkDataSource,
    private val authUserLocalDataSource: AuthUserLocalDataSource,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val authUserMapper: AuthUserMapper
) : LoginRepository {
    override suspend fun signIn(request: SignInRequest): ResultWrapper<SignInResponse> {
        return withContext(coroutineDispatcher) {
            signIn(
                request = request,
                networkSource = networkSource,
                localSource = authUserLocalDataSource,
                authUserMapper = authUserMapper
            )
        }
    }

    override suspend fun signUp(request: SignUpRequest): ResultWrapper<SignUpResponse> {
        return withContext(coroutineDispatcher) {
            signUp(
                request = request,
                networkSource = networkSource
            )
        }
    }

    companion object {
        private suspend fun signIn(
            request: SignInRequest,
            networkSource: LoginNetworkDataSource,
            localSource: AuthUserLocalDataSource,
            authUserMapper: AuthUserMapper
        ): ResultWrapper<SignInResponse> {
            return try {
                val signInRequest = LoginApi.Request(
                    email = request.email,
                    password = request.password
                )
                val signInResponse = networkSource.signIn(signInRequest)
                val user = insertUserAndSession(
                    userId = signInResponse.loggedInUserId,
                    email = request.email,
                    localSource = localSource,
                    authUserMapper = authUserMapper
                )
                val successData = SignInResponse(user = user)
                ResultWrapper.Success(successData)
            } catch (e: Exception) {
                ResultWrapper.Failure(
                    message = e.message.orEmpty(),
                    throwable = e
                )
            }
        }

        private suspend fun insertUserAndSession(
            userId: String,
            email: String,
            localSource: AuthUserLocalDataSource,
            authUserMapper: AuthUserMapper
        ): User {
            val authUser = AuthUserLocalModel(id = userId, email = email)
            val authSession = AuthSessionLocalModel(authUserId = userId, active = 1)
            localSource.insertAuthUser(authUser)
            localSource.insertAuthSession(authSession)
            return authUserMapper.authUserLocalModelToDomain(authUser)
        }

        private suspend fun signUp(
            request: SignUpRequest,
            networkSource: LoginNetworkDataSource
        ): ResultWrapper<SignUpResponse> {
            return try {
                val signInRequest = LoginApi.Request(
                    email = request.email,
                    password = request.password
                )
                val signInResponse = networkSource.signUp(signInRequest)
                val successData = SignUpResponse(loggedInUserId = signInResponse.loggedInUserId)
                ResultWrapper.Success(successData)
            } catch (e: Exception) {
                ResultWrapper.Failure(message = e.message.orEmpty())
            }
        }
    }
}

package com.mu.lastkey.feature.login.data.network

import com.mu.lastkey.core.network.realm.model.UserRealmModel
import com.mu.lastkey.feature.login.data.network.api.LoginApi
import com.mu.lastkey.feature.login.data.network.api.UserApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class LoginNetworkDataSourceImpl(
    private val loginApi: LoginApi,
    private val userApi: UserApi,
    private val coroutineDispatcher: CoroutineDispatcher
) : LoginNetworkDataSource {
    override suspend fun signIn(request: LoginApi.Request): LoginApi.Response {
        return withContext(coroutineDispatcher) {
            withTimeout(TIMEOUT) {
                loginApi.signIn(request)
            }
        }
    }

    override suspend fun signUp(request: LoginApi.Request): LoginApi.Response {
        return withContext(coroutineDispatcher) {
            withTimeout(TIMEOUT) {
                loginApi.signUp(request)
                val signInResponse = loginApi.signIn(request)
                val user = UserRealmModel().apply {
                    _id = org.mongodb.kbson.ObjectId(signInResponse.loggedInUserId)
                    email = request.email
                }
                userApi.createNewUser(user)
                signInResponse
            }
        }
    }

    companion object {
        private const val TIMEOUT = 10000L
    }
}

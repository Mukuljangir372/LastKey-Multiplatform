package com.mu.lastkey.feature.login.data.network

import com.mu.lastkey.core.network.realm.model.UserRealmModel
import com.mu.lastkey.feature.login.data.network.api.LoginApi
import com.mu.lastkey.feature.login.data.network.api.UserApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginNetworkDataSourceImpl(
    private val loginApi: LoginApi,
    private val userApi: UserApi,
    private val coroutineDispatcher: CoroutineDispatcher
) : LoginNetworkDataSource {
    override suspend fun signIn(request: LoginApi.Request): LoginApi.Response {
        return withContext(coroutineDispatcher) {
            loginApi.signIn(request)
        }
    }

    override suspend fun signUp(request: LoginApi.Request) {
        return withContext(coroutineDispatcher) {
            loginApi.signUp(request)
            val loggedInUserId = loginApi.signIn(request)
            val user = UserRealmModel().apply {
                _id = org.mongodb.kbson.ObjectId(loggedInUserId.loggedInUserId)
                email = request.email
            }
            userApi.createNewUser(user)
        }
    }
}

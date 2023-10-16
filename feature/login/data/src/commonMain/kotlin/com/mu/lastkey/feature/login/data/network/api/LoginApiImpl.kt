package com.mu.lastkey.feature.login.data.network.api

import com.mu.lastkey.core.network.realm.RealmClient
import io.realm.kotlin.mongodb.Credentials

class LoginApiImpl(
    private val realmClient: RealmClient
) : LoginApi {
    override suspend fun signIn(request: LoginApi.Request): LoginApi.Response {
        val credentials = Credentials.emailPassword(request.email, request.password)
        val loggedInUser = realmClient.getApp().login(credentials)
        return LoginApi.Response(loggedInUserId = loggedInUser.id)
    }

    override suspend fun signUp(request: LoginApi.Request) {
        realmClient.getApp()
            .emailPasswordAuth
            .registerUser(request.email, request.password)
    }

    override suspend fun isLoggedIn(): Boolean {
        return realmClient.getApp().currentUser != null
    }
}

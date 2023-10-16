package com.mu.lastkey.feature.login.data.network

import com.mu.lastkey.feature.login.data.network.api.LoginApi

interface LoginNetworkDataSource {
    suspend fun signIn(request: LoginApi.Request): LoginApi.Response
    suspend fun signUp(request: LoginApi.Request)
}

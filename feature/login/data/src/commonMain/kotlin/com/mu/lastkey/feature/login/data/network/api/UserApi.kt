package com.mu.lastkey.feature.login.data.network.api

import com.mu.lastkey.core.network.realm.model.UserRealmModel

interface UserApi {
    suspend fun getLoggedInUser(): UserRealmModel
    suspend fun getUser(id: String): UserRealmModel
    suspend fun createNewUser(user: UserRealmModel)
}

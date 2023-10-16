package com.mu.lastkey.feature.login.data.network.api

import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.core.network.realm.model.UserRealmModel
import com.mu.lastkey.feature.login.data.network.exception.UserNotFoundException
import com.mu.lastkey.feature.login.data.network.exception.UserNotLoggedInException
import io.realm.kotlin.UpdatePolicy
import org.mongodb.kbson.ObjectId

class UserApiImpl(private val realmClient: RealmClient) : UserApi {
    override suspend fun getLoggedInUser(): UserRealmModel {
        val loggedInUserId = realmClient.getApp().currentUser?.id ?: throw UserNotLoggedInException()
        return getUser(loggedInUserId)
    }

    override suspend fun getUser(id: String): UserRealmModel {
        val realm = realmClient.getOpenedRealm()
        return realm.query(
            clazz = UserRealmModel::class,
            query = "_id == %0",
            ObjectId(id)
        ).first().find() ?: throw UserNotFoundException()
    }

    override suspend fun createNewUser(user: UserRealmModel) {
        val realm = realmClient.getOpenedRealm()
        realm.writeBlocking {
            copyToRealm(instance = user, updatePolicy = UpdatePolicy.ALL)
        }
    }
}

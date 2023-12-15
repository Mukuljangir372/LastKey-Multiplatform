package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.AuthSessionLocalModel
import com.mu.lastkey.core.data.local.model.AuthUserLocalModel
import com.mu.lastkey.core.domain.model.user.AuthSession
import com.mu.lastkey.core.domain.model.user.User
import commulastkeycoredata.AuthSessionEntity
import commulastkeycoredata.AuthUserEntity

internal class AuthUserMapperImpl : AuthUserMapper {
    override fun authUserEntityToLocal(model: AuthUserEntity): AuthUserLocalModel {
        return AuthUserLocalModel(
            id = model.id,
            email = model.email
        )
    }

    override fun authSessionEntityToLocal(model: AuthSessionEntity): AuthSessionLocalModel {
        return AuthSessionLocalModel(
            authUserId = model.authUserId,
            active = model.active.toInt()
        )
    }

    override fun authUserLocalModelToDomain(model: AuthUserLocalModel): User {
        return User(
            id = model.id,
            email = model.email
        )
    }

    override fun authSessionLocalToDomain(model: AuthSessionLocalModel): AuthSession {
        return AuthSession(
            authUserId = model.authUserId,
            active = model.active == 1
        )
    }
}

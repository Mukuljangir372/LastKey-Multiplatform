package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.AuthSessionLocalModel
import com.mu.lastkey.core.data.local.model.AuthUserLocalModel
import com.mu.lastkey.core.domain.model.user.User
import commulastkeycoredata.AuthSessionEntity
import commulastkeycoredata.AuthUserEntity

class AuthUserMapperImpl : AuthUserMapper {
    override fun authUserEntityToLocalModel(model: AuthUserEntity): AuthUserLocalModel {
        return AuthUserLocalModel(
            id = model.id,
            email = model.email
        )
    }

    override fun authSessionEntityToLocalModel(model: AuthSessionEntity): AuthSessionLocalModel {
        return AuthSessionLocalModel(
            authUserId = model.authUserId,
            active = model.active.toInt()
        )
    }

    override fun authUserLocalModelToUser(model: AuthUserLocalModel): User {
        return User(
            id = model.id,
            email = model.email
        )
    }
}

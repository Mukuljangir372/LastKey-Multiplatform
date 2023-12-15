package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.AuthSessionLocalModel
import com.mu.lastkey.core.data.local.model.AuthUserLocalModel
import com.mu.lastkey.core.domain.model.user.AuthSession
import com.mu.lastkey.core.domain.model.user.User
import commulastkeycoredata.AuthSessionEntity
import commulastkeycoredata.AuthUserEntity

interface AuthUserMapper {
    fun authUserEntityToLocal(model: AuthUserEntity): AuthUserLocalModel
    fun authSessionEntityToLocal(model: AuthSessionEntity): AuthSessionLocalModel
    fun authUserLocalModelToDomain(model: AuthUserLocalModel): User
    fun authSessionLocalToDomain(model: AuthSessionLocalModel): AuthSession
}

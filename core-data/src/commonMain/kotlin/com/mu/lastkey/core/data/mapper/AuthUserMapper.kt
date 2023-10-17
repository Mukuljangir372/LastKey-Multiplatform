package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.AuthSessionLocalModel
import com.mu.lastkey.core.data.local.model.AuthUserLocalModel
import com.mu.lastkey.core.domain.model.user.User
import commulastkeycoredata.AuthSessionEntity
import commulastkeycoredata.AuthUserEntity

interface AuthUserMapper {
    fun authUserEntityToLocalModel(model: AuthUserEntity): AuthUserLocalModel
    fun authSessionEntityToLocalModel(model: AuthSessionEntity): AuthSessionLocalModel
    fun authUserLocalModelToUser(model: AuthUserLocalModel): User
}

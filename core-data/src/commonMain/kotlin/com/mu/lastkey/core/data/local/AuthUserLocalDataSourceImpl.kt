package com.mu.lastkey.core.data.local

import com.mu.lastkey.core.data.AppDatabase
import com.mu.lastkey.core.data.local.model.AuthSessionLocalModel
import com.mu.lastkey.core.data.local.model.AuthUserLocalModel
import com.mu.lastkey.core.data.mapper.AuthUserMapper

class AuthUserLocalDataSourceImpl(
    private val appDatabase: AppDatabase,
    private val authUserMapper: AuthUserMapper
) : AuthUserLocalDataSource {
    override suspend fun getLoggedInAuthUser(): AuthUserLocalModel? {
        val loggedInUserId = getActiveAuthSession()?.authUserId
        return loggedInUserId?.let { getAuthUser(it) }
    }

    override suspend fun getAuthUser(id: String): AuthUserLocalModel? {
        val entity = appDatabase.authuserQueries.getAuthUserById(id).executeAsOneOrNull()
        return entity?.let { authUserMapper.authUserEntityToLocalModel(it) }
    }

    override suspend fun getActiveAuthSession(): AuthSessionLocalModel? {
        val entity = appDatabase.authuserQueries.getActiveAuthSession().executeAsOneOrNull()
        return entity?.let { authUserMapper.authSessionEntityToLocalModel(it) }
    }

    override suspend fun insertAuthUser(model: AuthUserLocalModel) {
        appDatabase.authuserQueries.insertOrReplaceAuthUser(
            id = model.id,
            email = model.email
        )
    }

    override suspend fun insertAuthSession(model: AuthSessionLocalModel) {
        appDatabase.authuserQueries.insertOrReplaceAuthSession(
            authUserId = model.authUserId,
            active = model.active.toLong()
        )
    }
}

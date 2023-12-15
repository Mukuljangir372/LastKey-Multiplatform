package com.mu.lastkey.core.data.repository

import com.mu.lastkey.core.data.local.AuthUserLocalDataSource
import com.mu.lastkey.core.data.mapper.AuthUserMapper
import com.mu.lastkey.core.domain.model.user.AuthSession
import com.mu.lastkey.core.domain.repository.AuthUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class AuthUserRepositoryImpl(
    private val localDataSource: AuthUserLocalDataSource,
    private val mapper: AuthUserMapper,
    private val coroutineDispatcher: CoroutineDispatcher
) : AuthUserRepository {
    override suspend fun getActiveAuthSession(): AuthSession? {
        return withContext(coroutineDispatcher) {
            getActiveAuthSession(
                localDataSource = localDataSource,
                mapper = mapper
            )
        }
    }

    companion object {
        private suspend fun getActiveAuthSession(
            localDataSource: AuthUserLocalDataSource,
            mapper: AuthUserMapper
        ): AuthSession? {
            val session = localDataSource.getActiveAuthSession()
            return session?.let { mapper.authSessionLocalToDomain(it) }
        }
    }
}

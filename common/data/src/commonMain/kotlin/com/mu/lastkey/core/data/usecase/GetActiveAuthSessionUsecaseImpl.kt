package com.mu.lastkey.core.data.usecase

import com.mu.lastkey.core.domain.model.user.AuthSession
import com.mu.lastkey.core.domain.repository.AuthUserRepository
import com.mu.lastkey.core.domain.usecase.GetActiveAuthSessionUsecase

internal class GetActiveAuthSessionUsecaseImpl(
    private val authUserRepository: AuthUserRepository
) : GetActiveAuthSessionUsecase {
    override suspend fun invoke(): AuthSession? {
        return authUserRepository.getActiveAuthSession()
    }
}

package com.mu.lastkey.core.domain.usecase

import com.mu.lastkey.core.domain.model.user.AuthSession

interface GetActiveAuthSessionUsecase {
    suspend operator fun invoke(): AuthSession?
}

package com.mu.lastkey.feature.password.domain.usecase

import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.domain.model.credential.Credential

interface GetCredentialsUsecase {
    data class Params(
        val offset: Int,
        val refresh: Boolean,
        val pagingKey: String
    )

    suspend operator fun invoke(params: Params): ResultWrapper<List<Credential>>
}

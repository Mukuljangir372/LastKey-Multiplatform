package com.mu.lastkey.feature.password.domain.usecase

import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.domain.model.credential.Credential
import com.mu.lastkey.feature.password.domain.repository.CredentialRepository

class GetCredentialsUsecaseImpl(
    private val repository: CredentialRepository
) : GetCredentialsUsecase {
    override suspend fun invoke(
        params: GetCredentialsUsecase.Params
    ): ResultWrapper<List<Credential>> {
        return repository.getCredentials(
            offset = params.offset,
            refresh = params.refresh,
            pagingKey = params.pagingKey
        )
    }
}

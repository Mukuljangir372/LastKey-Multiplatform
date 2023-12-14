package com.mu.lastkey.feature.password.domain.di

import com.mu.lastkey.feature.password.domain.repository.CredentialRepository
import com.mu.lastkey.feature.password.domain.usecase.GetCredentialsUsecase
import com.mu.lastkey.feature.password.domain.usecase.GetCredentialsUsecaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCredentialDomainModule(): Module {
    return module {
        single { provideGetCredentialsUsecase(get()) }
    }
}

private fun provideGetCredentialsUsecase(
    repository: CredentialRepository
): GetCredentialsUsecase {
    return GetCredentialsUsecaseImpl(
        repository = repository
    )
}

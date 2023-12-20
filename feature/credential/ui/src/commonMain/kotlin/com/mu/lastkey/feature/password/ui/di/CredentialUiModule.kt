package com.mu.lastkey.feature.password.ui.di

import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.feature.password.domain.usecase.GetCredentialsUsecase
import com.mu.lastkey.feature.password.ui.CredentialListViewModel
import com.mu.lastkey.feature.password.ui.model.CredentialModelMapper
import com.mu.lastkey.feature.password.ui.model.CredentialModelMapperImpl
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCredentialUiModule(): Module {
    return module {
        single { provideCredentialModelMapper() }
        single { providePasswordListViewModel(get(), get(), get()) }
    }
}

private fun providePasswordListViewModel(
    dispatchers: AppCoroutineDispatchers,
    getCredentialsUsecase: GetCredentialsUsecase,
    credentialModelMapper: CredentialModelMapper
): CredentialListViewModel {
    return CredentialListViewModel(
        dispatchers = dispatchers,
        getCredentialsUsecase = getCredentialsUsecase,
        credentialModelMapper = credentialModelMapper
    )
}

private fun provideCredentialModelMapper(): CredentialModelMapper {
    return CredentialModelMapperImpl()
}

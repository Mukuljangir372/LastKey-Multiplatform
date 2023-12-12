package com.mu.lastkey.feature.password.ui.di

import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.feature.password.ui.CredentialListViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCredentialUiModule(): Module {
    return module {
        single { providePasswordListViewModel(get()) }
    }
}

private fun providePasswordListViewModel(
    dispatchers: AppCoroutineDispatchers
): CredentialListViewModel {
    return CredentialListViewModel(
        dispatchers = dispatchers
    )
}

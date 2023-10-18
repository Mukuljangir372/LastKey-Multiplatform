package com.mu.lastkey.feature.login.ui.di

import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.feature.login.domain.usecase.SignInUsecase
import com.mu.lastkey.feature.login.ui.signin.SignInViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun getLoginUiModule(): Module {
    return module {
        single { provideSignInViewModel(get(), get()) }
    }
}

private fun provideSignInViewModel(
    signInUsecase: SignInUsecase,
    dispatchers: AppCoroutineDispatchers
): SignInViewModel {
    return SignInViewModel(
        signInUsecase = signInUsecase,
        dispatchers = dispatchers
    )
}

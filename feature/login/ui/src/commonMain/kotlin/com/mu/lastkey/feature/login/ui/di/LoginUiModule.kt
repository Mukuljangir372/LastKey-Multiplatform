package com.mu.lastkey.feature.login.ui.di

import com.mu.lastkey.feature.login.domain.usecase.SignInUsecase
import com.mu.lastkey.feature.login.ui.SignInViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun getLoginUiModule(): Module {
    return module {
        single { provideSignInViewModel(get()) }
    }
}

private fun provideSignInViewModel(
    signInUsecase: SignInUsecase
): SignInViewModel {
    return SignInViewModel(signInUsecase = signInUsecase)
}

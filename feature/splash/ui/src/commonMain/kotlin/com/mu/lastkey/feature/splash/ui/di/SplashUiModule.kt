package com.mu.lastkey.feature.splash.ui.di

import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.core.domain.usecase.GetActiveAuthSessionUsecase
import com.mu.lastkey.feature.splash.ui.SplashViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSplashUiModule(): Module {
    return module {
        single { provideSplashViewModel(get(), get()) }
    }
}

private fun provideSplashViewModel(
    dispatchers: AppCoroutineDispatchers,
    getActiveAuthSessionUsecase: GetActiveAuthSessionUsecase
): SplashViewModel {
    return SplashViewModel(
        getActiveAuthSessionUsecase = getActiveAuthSessionUsecase,
        dispatchers = dispatchers
    )
}
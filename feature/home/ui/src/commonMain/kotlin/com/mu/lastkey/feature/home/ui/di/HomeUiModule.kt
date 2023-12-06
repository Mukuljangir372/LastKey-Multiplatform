package com.mu.lastkey.feature.home.ui.di

import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.feature.home.ui.HomeViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun getHomeUiModule(): Module {
    return module {
        single { provideHomeViewModel(get()) }
    }
}

private fun provideHomeViewModel(
    dispatchers: AppCoroutineDispatchers
): HomeViewModel {
    return HomeViewModel(
        dispatchers = dispatchers
    )
}

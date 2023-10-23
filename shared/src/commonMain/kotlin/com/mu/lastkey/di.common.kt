package com.mu.lastkey

import com.mu.lastkey.core.data.getCoreDataModule
import com.mu.lastkey.core.data.getCoreDataPlatformModule
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.core.logging.di.getCoreLoggingModule
import com.mu.lastkey.core.network.di.getCoreNetworkModule
import com.mu.lastkey.core.preferences.di.getCorePreferencesModule
import com.mu.lastkey.core.preferences.getCorePreferencesPlatformModule
import com.mu.lastkey.core.ui.navigation.AppNavigation
import com.mu.lastkey.core.utils.di.getCoreUtilsModule
import com.mu.lastkey.dashboard.DashboardViewModel
import com.mu.lastkey.feature.home.ui.di.getHomeUiModule
import com.mu.lastkey.feature.login.data.di.getLoginDataModule
import com.mu.lastkey.feature.login.ui.di.getLoginUiModule
import com.mu.lastkey.navigation.AppNavigationImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect class DependencyGraph {
    fun load()
}

internal val dependencies = listOf(
    getCoreLoggingModule(),
    getCoreNetworkModule(),
    getCorePreferencesModule(),
    getCoreUtilsModule(),
    getCorePreferencesPlatformModule(),
    getCoreDataPlatformModule(),
    getCoreDataModule(),
    getLoginDataModule(),
    getLoginUiModule(),
    getSharedModule(),
    getHomeUiModule()
)

private fun getSharedModule(): Module {
    return module {
        single { provideAppNavigation() }
        single { provideDashboardViewModel(get()) }
    }
}

private fun provideAppNavigation(): AppNavigation {
    return AppNavigationImpl()
}

private fun provideDashboardViewModel(
    dispatchers: AppCoroutineDispatchers
): DashboardViewModel {
    return DashboardViewModel(
        dispatchers = dispatchers
    )
}

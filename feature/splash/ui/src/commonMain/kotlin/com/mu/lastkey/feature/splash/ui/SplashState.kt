package com.mu.lastkey.feature.splash.ui

import androidx.compose.runtime.Stable

internal data class SplashState(
    val loading: Boolean,
    val login: Boolean,
    val dashboard: Boolean
) {
    companion object {
        val idle = SplashState(
            loading = false,
            login = false,
            dashboard = false
        )
    }
}

@Stable
internal sealed interface SplashUiState {
    data object Loading : SplashUiState
    data object Login : SplashUiState
    data object Dashboard : SplashUiState
}

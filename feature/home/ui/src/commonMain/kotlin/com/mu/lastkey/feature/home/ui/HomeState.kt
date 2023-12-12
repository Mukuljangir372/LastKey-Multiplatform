package com.mu.lastkey.feature.home.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
internal data class HomeState(
    val loading: Boolean
) {
    companion object {
        val idle = HomeState(
            loading = false
        )
    }
}

@Immutable
internal sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object Home : HomeUiState
}

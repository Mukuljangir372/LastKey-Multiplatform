package com.mu.lastkey.home

import androidx.compose.runtime.Stable

internal data class HomeState(
    val loading: Boolean
) {
    companion object {
        val idle = HomeState(
            loading = false
        )
    }
}

@Stable
internal sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object Home : HomeUiState
}

package com.mu.lastkey.home

internal data class HomeState(
    val loading: Boolean
) {
    companion object {
        val idle = HomeState(
            loading = false
        )
    }
}

internal sealed interface HomeUiState {
    data object Idle : HomeUiState
    data object Loading : HomeUiState
    data object Home : HomeUiState
}

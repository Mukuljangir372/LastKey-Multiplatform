package com.mu.lastkey.feature.home.ui

data class HomeState(
    val loading: Boolean
) {
    companion object {
        val idle = HomeState(
            loading = false
        )
    }
}

sealed interface HomeUiState {
    object Idle : HomeUiState
    object Loading : HomeUiState
    object Home : HomeUiState
}

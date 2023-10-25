package com.mu.lastkey.home

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

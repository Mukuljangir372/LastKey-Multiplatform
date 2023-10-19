package com.mu.lastkey.dashboard

class DashboardState {
    companion object {
        val idle = DashboardState()
    }
}

sealed interface DashboardUiState {
    object Idle : DashboardUiState
}

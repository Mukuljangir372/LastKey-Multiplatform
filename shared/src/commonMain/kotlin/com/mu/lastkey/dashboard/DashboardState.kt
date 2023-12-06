package com.mu.lastkey.dashboard

internal class DashboardState {
    companion object {
        val idle = DashboardState()
    }
}

internal sealed interface DashboardUiState {
    data object Idle : DashboardUiState
}

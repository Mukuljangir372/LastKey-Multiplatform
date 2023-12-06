package com.mu.lastkey.dashboard

import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class DashboardViewModel(
    private val dispatchers: AppCoroutineDispatchers
) : ScreenModel {
    private var scope: CoroutineScope? = null

    private val _state = MutableStateFlow(DashboardState.idle)
    val state: Flow<DashboardUiState> get() = _state.map { mapDashboardStateToUiState(it) }

    private fun resetScope() {
        scope?.cancel()
        scope = null
        scope = CoroutineScope(dispatchers.main)
    }

    fun start() {
        resetScope()
    }

    companion object {
        private fun mapDashboardStateToUiState(
            state: DashboardState
        ): DashboardUiState {
            return DashboardUiState.Idle
        }
    }
}

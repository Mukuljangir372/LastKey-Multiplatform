package com.mu.lastkey.home

import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val dispatchers: AppCoroutineDispatchers
) : ScreenModel {
    private val state = MutableStateFlow(HomeState.idle)
    val uiState: Flow<HomeUiState>
        get() = state.map { mapStateToUiState(it) }.flowOn(dispatchers.default)

    private var scope: CoroutineScope? = null

    private fun resetScope() {
        scope?.cancel()
        scope = CoroutineScope(dispatchers.default)
    }

    override fun onDispose() {
        resetScope()
    }

    fun start() {
        resetScope()
    }

    companion object {
        private fun mapStateToUiState(state: HomeState): HomeUiState {
            return if (state.loading) {
                HomeUiState.Loading
            } else {
                HomeUiState.Home
            }
        }
    }
}

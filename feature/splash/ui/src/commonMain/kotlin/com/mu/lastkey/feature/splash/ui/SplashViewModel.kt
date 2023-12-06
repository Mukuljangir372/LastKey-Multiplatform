package com.mu.lastkey.feature.splash.ui

import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.core.domain.usecase.GetActiveAuthSessionUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SplashViewModel(
    private val getActiveAuthSessionUsecase: GetActiveAuthSessionUsecase,
    private val dispatchers: AppCoroutineDispatchers
) : ScreenModel {
    private val _state = MutableStateFlow(SplashState.idle)
    val uiState: Flow<SplashUiState>
        get() = _state.map { mapStateToUiState(it) }.flowOn(dispatchers.default)

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
        load()
    }

    private fun load() {
        scope?.launch {
            delay(SPLASH_DELAY)
            val state = getActiveAuthSessionAsState(
                state = _state,
                getActiveAuthSessionUsecase = getActiveAuthSessionUsecase
            )
            _state.update { state }
        }
    }

    companion object {
        private const val SPLASH_DELAY = 1000L

        private fun mapStateToUiState(state: SplashState): SplashUiState {
            return if (state.login) {
                SplashUiState.Login
            } else if (state.dashboard) {
                SplashUiState.Dashboard
            } else {
                SplashUiState.Loading
            }
        }

        private suspend fun getActiveAuthSessionAsState(
            state: StateFlow<SplashState>,
            getActiveAuthSessionUsecase: GetActiveAuthSessionUsecase
        ): SplashState {
            val session = getActiveAuthSessionUsecase()
            val active = session != null && session.active
            return state.value.copy(
                loading = false,
                login = !active,
                dashboard = active
            )
        }
    }
}

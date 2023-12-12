package com.mu.lastkey.feature.password.ui

import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class PasswordListViewModel(
    private val dispatchers: AppCoroutineDispatchers
) : ScreenModel {
    private var scope: CoroutineScope? = null

    private val state = MutableStateFlow(PasswordListState.idle)
    val uiState: Flow<PasswordListUiState> get() = state.map { mapStateToUiState(it) }

    private fun resetScope() {
        scope?.cancel()
        scope = CoroutineScope(dispatchers.default)
    }

    override fun onDispose() {
        super.onDispose()
        resetScope()
    }

    fun start() {
        resetScope()
    }

    companion object {
        private fun mapStateToUiState(
            state: PasswordListState
        ): PasswordListUiState {
            return if (state.loading) {
                PasswordListUiState.Loading
            } else if (state.passwords.isNotEmpty()) {
                PasswordListUiState.Passwords(passwords = state.passwords)
            } else {
                PasswordListUiState.NoResults
            }
        }
    }
}

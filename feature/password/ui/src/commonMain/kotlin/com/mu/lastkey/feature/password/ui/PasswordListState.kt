package com.mu.lastkey.feature.password.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.mu.lastkey.feature.password.ui.mock.PasswordListMock
import com.mu.lastkey.feature.password.ui.model.PasswordDisplayModel

@Stable
internal data class PasswordListState(
    val loading: Boolean,
    val passwords: List<PasswordDisplayModel>
) {
    companion object {
        val idle = PasswordListState(
            loading = false,
            passwords = PasswordListMock.passwords
        )
    }
}

@Immutable
internal sealed interface PasswordListUiState {
    data object Loading : PasswordListUiState
    data class Passwords(val passwords: List<PasswordDisplayModel>) : PasswordListUiState
    data object NoResults : PasswordListUiState
}

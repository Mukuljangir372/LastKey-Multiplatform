package com.mu.lastkey.feature.password.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.mu.lastkey.feature.password.ui.mock.CredentialListMock
import com.mu.lastkey.feature.password.ui.model.CredentialDisplayModel

@Stable
internal data class CredentialListState(
    val loading: Boolean,
    val credentials: List<CredentialDisplayModel>
) {
    companion object {
        val idle = CredentialListState(
            loading = false,
            credentials = CredentialListMock.credentials
        )
    }
}

@Immutable
internal sealed interface CredentialListUiState {
    data object Loading : CredentialListUiState

    @Stable
    data class Credentials(val list: List<CredentialDisplayModel>) : CredentialListUiState
    data object NoResults : CredentialListUiState
}

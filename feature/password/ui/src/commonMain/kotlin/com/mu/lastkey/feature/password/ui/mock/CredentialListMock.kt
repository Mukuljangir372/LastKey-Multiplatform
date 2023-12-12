package com.mu.lastkey.feature.password.ui.mock

import com.mu.lastkey.feature.password.ui.model.CredentialDisplayModel

internal object CredentialListMock {
    val credentials = listOf(
        CredentialDisplayModel(
            id = "1",
            name = "Netflix",
            description = "LastKey@netflix.com"
        ),
        CredentialDisplayModel(
            id = "2",
            name = "Youtube",
            description = "LastKey@youtube.com"
        ),
        CredentialDisplayModel(
            id = "3",
            name = "Amazon Prime",
            description = "LastKey@amazon.com"
        ),
        CredentialDisplayModel(
            id = "4",
            name = "Google",
            description = "LastKey@google.com"
        ),
        CredentialDisplayModel(
            id = "5",
            name = "Medium",
            description = "LastKey@medium.com"
        )
    )
}

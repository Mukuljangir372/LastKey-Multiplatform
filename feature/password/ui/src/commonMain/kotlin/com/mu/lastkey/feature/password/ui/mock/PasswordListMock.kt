package com.mu.lastkey.feature.password.ui.mock

import com.mu.lastkey.feature.password.ui.model.PasswordDisplayModel

internal object PasswordListMock {
    val passwords = listOf(
        PasswordDisplayModel(
            id = "1",
            name = "Netflix",
            description = "LastKey@netflix.com"
        ),
        PasswordDisplayModel(
            id = "2",
            name = "Youtube",
            description = "LastKey@youtube.com"
        ),
        PasswordDisplayModel(
            id = "3",
            name = "Amazon Prime",
            description = "LastKey@amazon.com"
        ),
        PasswordDisplayModel(
            id = "4",
            name = "Google",
            description = "LastKey@google.com"
        ),
        PasswordDisplayModel(
            id = "5",
            name = "Medium",
            description = "LastKey@medium.com"
        )
    )
}

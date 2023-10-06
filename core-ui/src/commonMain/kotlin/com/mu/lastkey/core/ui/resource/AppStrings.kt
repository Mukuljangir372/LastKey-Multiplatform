package com.mu.lastkey.core.ui.resource

import androidx.compose.runtime.Stable

@Stable
data class AppStrings(
    val appName: String,
    val welcomeToAppLabel: String,
    val appDescription: String
) {
    companion object {
        val en = AppStrings(
            appName = "LastKey",
            welcomeToAppLabel = "Welcome to LastKey!",
            appDescription = "Securely manage passwords, notes and chats."
        )
    }
}

package com.mu.lastkey.core.ui.resource

import androidx.compose.runtime.Stable

@Stable
data class AppStrings(
    val appName: String,
    val welcomeToAppLabel: String,
    val appDescription: String,
    val login: String,
    val createNewAccount: String,
    val or: String,
    val forgotPassword: String
) {
    companion object {
        val en = AppStrings(
            appName = "LastKey",
            welcomeToAppLabel = "Welcome to LastKey!",
            appDescription = "Securely manage passwords, notes and chats.",
            login = "Continue to Login",
            createNewAccount = "Create New Account",
            or = "OR",
            forgotPassword = "Forgot Password?"
        )
    }
}
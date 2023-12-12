package com.mu.lastkey.core.domain.model

data class AppStrings(
    val appName: String,
    val welcomeToAppLabel: String,
    val appDescription: String,
    val login: String,
    val createNewAccount: String,
    val or: String,
    val forgotPassword: String,
    val email: String,
    val password: String,
    val signedInSuccess: String,
    val signedUpSuccess: String,
    val enterValidEmail: String,
    val enterValidPassword: String,
    val passwords: String,
    val home: String,
    val chats: String,
    val search: String,
    val more: String,
    val profile: String,
    val viewProfile: String,
    val noResults: String
) {
    companion object {
        val en = AppStrings(
            appName = "LastKey",
            welcomeToAppLabel = "Welcome to LastKey!",
            appDescription = "Securely manage passwords, notes and chats.",
            login = "Continue to Login",
            createNewAccount = "Create New Account",
            or = "OR",
            forgotPassword = "Forgot Password?",
            email = "Email",
            password = "Password",
            signedInSuccess = "Signed in successfully!",
            signedUpSuccess = "New account created successfully!",
            enterValidEmail = "Please enter a valid email",
            enterValidPassword = "Please enter a valid password. Min length is 6 chars.",
            passwords = "Passwords",
            home = "Home",
            chats = "Chats",
            search = "Search",
            more = "More",
            profile = "Profile",
            viewProfile = "View Profile",
            noResults = "No Results"
        )
    }
}

package com.mu.lastkey.feature.login.data.network.exception

class UserNotLoggedInException : Exception() {
    override val message: String
        get() = "User not logged in!"
}

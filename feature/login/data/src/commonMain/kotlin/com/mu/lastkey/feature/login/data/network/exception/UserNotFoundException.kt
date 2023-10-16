package com.mu.lastkey.feature.login.data.network.exception

class UserNotFoundException : Exception() {
    override val message: String
        get() = "User not found!"
}

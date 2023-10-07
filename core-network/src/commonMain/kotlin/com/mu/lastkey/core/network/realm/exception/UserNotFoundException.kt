package com.mu.lastkey.core.network.realm.exception

class UserNotFoundException : Exception() {
    override val message: String
        get() = "User not found! Please login first!"
}

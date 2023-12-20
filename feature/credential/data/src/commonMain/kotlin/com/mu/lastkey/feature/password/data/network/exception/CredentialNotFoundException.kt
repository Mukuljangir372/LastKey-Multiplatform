package com.mu.lastkey.feature.password.data.network.exception

internal class CredentialNotFoundException(
    private val id: String
) : Exception() {
    override val message: String get() = "Credential Not Found By ID: $id"
}
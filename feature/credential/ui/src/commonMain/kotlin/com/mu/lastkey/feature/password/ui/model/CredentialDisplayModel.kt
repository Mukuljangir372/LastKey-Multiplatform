package com.mu.lastkey.feature.password.ui.model

import androidx.compose.runtime.Stable

@Stable
internal data class CredentialDisplayModel(
    val id: String,
    val name: String,
    val description: String
)

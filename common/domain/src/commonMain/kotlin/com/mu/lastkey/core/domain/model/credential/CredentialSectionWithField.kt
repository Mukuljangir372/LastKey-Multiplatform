package com.mu.lastkey.core.domain.model.credential

data class CredentialSectionWithField(
    val section: CredentialSection,
    val fields: List<CredentialSectionField>
)

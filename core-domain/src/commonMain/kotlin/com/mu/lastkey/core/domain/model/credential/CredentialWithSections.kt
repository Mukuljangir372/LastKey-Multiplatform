package com.mu.lastkey.core.domain.model.credential

data class CredentialWithSections(
    val credential: Credential,
    val sections: List<CredentialSection>
)

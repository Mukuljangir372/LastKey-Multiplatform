package com.mu.lastkey.core.network.realm

// NOTE: THIS FILE HOLD SECURE KEYS, PLEASE DON"T PUSH TO VERSION CONTROL.
// THIS METHOD SHOULD BE CREATE FROM DEVELOPER SIDE WITH HIS/HER KEYS.
class RealmConfigFactory {
    fun create(): RealmConfig {
        return RealmConfig(
            appId = "",
            apiKey = ""
        )
    }
}

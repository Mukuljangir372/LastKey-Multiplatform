package com.mu.lastkey.core.network.realm

import com.mu.lastkey.core.network.BuildConfig

class RealmConfigFactory {
    fun create(): RealmConfig {
        return RealmConfig(
            appId = BuildConfig.REALM_APP_ID,
            apiKey = BuildConfig.REALM_API_KEY
        )
    }
}

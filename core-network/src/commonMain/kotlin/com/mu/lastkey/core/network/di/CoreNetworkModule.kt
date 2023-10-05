package com.mu.lastkey.core.network.di

import com.mu.lastkey.core.network.HttpClientFactory
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

fun getCoreNetworkModule(): DI.Module {
    return DI.Module(name = "coreNetworkModule") {
        bindSingleton { HttpClientFactory().create(instance()) }
    }
}

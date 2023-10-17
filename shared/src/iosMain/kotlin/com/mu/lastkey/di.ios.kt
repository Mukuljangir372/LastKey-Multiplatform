package com.mu.lastkey

import org.koin.core.context.startKoin

actual class DependencyGraph {
    actual fun load() {
        startKoin {
            modules(dependencies)
        }
    }
}

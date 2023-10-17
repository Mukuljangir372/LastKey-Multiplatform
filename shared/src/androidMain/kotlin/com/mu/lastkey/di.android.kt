package com.mu.lastkey

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

actual class DependencyGraph(private val application: Application) {
    actual fun load() {
        startKoin {
            androidContext(application)
            modules(dependencies)
        }
    }
}

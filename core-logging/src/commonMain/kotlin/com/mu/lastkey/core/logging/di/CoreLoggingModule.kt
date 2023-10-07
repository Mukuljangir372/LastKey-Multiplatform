package com.mu.lastkey.core.logging.di

import com.mu.lastkey.core.logging.DebugLogTracker
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DEBUG_LOG_TRACKER = "DEBUG_LOG_TRACKER"

fun getCoreLoggingModule(): Module {
    return module {
        single(named(DEBUG_LOG_TRACKER)) { DebugLogTracker() }
    }
}

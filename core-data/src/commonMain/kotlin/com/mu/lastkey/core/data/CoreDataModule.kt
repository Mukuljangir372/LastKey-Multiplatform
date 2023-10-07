package com.mu.lastkey.core.data

import com.mu.lastkey.core.domain.model.coroutine.AppCoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreDataModule(): Module {
    return module {
        single { provideAppCoroutineDispatchers() }
    }
}

private fun provideAppCoroutineDispatchers(): AppCoroutineDispatchers {
    return AppCoroutineDispatchers(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        default = Dispatchers.Default
    )
}

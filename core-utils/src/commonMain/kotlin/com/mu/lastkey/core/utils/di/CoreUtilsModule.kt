package com.mu.lastkey.core.utils.di

import com.mu.lastkey.core.utils.coroutine.AppCoroutineDispatchers
import com.mu.lastkey.core.utils.device.DeviceConfig
import com.mu.lastkey.core.utils.json.JsonFactory
import com.mu.lastkey.core.utils.validation.BasicValidator
import com.mu.lastkey.core.utils.validation.BasicValidatorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreUtilsModule(): Module {
    return module {
        single { provideJson() }
        single { provideAppCoroutineDispatchers() }
        single { provideDeviceConfig() }
        single { provideBasicValidator() }
    }
}

private fun provideJson(): Json {
    return JsonFactory().create()
}

private fun provideAppCoroutineDispatchers(): AppCoroutineDispatchers {
    return AppCoroutineDispatchers(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        default = Dispatchers.Default
    )
}

private fun provideDeviceConfig(): DeviceConfig {
    return DeviceConfig(type = DeviceConfig.Type.MOBILE)
}

private fun provideBasicValidator(): BasicValidator {
    return BasicValidatorImpl()
}

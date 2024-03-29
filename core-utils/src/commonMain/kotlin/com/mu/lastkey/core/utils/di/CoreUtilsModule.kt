package com.mu.lastkey.core.utils.di

import com.mu.lastkey.core.utils.device.DeviceConfig
import com.mu.lastkey.core.utils.json.JsonFactory
import com.mu.lastkey.core.utils.uuid.UUIDGenerator
import com.mu.lastkey.core.utils.uuid.UUIDGeneratorImpl
import com.mu.lastkey.core.utils.validation.BasicValidator
import com.mu.lastkey.core.utils.validation.BasicValidatorImpl
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreUtilsModule(): Module {
    return module {
        single { provideJson() }
        single { provideDeviceConfig() }
        single { provideBasicValidator() }
        single { provideUUIDGenerator() }
    }
}

private fun provideJson(): Json {
    return JsonFactory().create()
}

private fun provideDeviceConfig(): DeviceConfig {
    return DeviceConfig(type = DeviceConfig.Type.MOBILE)
}

private fun provideBasicValidator(): BasicValidator {
    return BasicValidatorImpl()
}

private fun provideUUIDGenerator(): UUIDGenerator {
    return UUIDGeneratorImpl()
}

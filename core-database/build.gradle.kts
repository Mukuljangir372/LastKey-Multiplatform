plugins {
    id("com.mu.lastkey.kmm")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}
kotlin {
    sourceSets {
        val androidMain by getting {
            dependencies {
                api(libs.sqldelight.android)
            }
        }
        val iosMain by getting {
            dependencies {
                api(libs.sqldelight.native)
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlin.serializaton)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.database"
}

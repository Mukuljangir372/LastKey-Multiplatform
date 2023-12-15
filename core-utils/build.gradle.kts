plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koin)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.serializaton)
                implementation(libs.kotlinx.datetime)
                implementation(libs.realm)
                implementation(libs.store)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.utils"
}

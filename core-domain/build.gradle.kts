plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlinx.datetime)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.domain"
}

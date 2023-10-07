plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.coroutines.core)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.domain"
}

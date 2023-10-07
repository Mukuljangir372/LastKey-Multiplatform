plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.napier)
                implementation(libs.koin)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.logging"
}

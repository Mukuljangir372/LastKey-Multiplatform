plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.napier)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.logging"
}

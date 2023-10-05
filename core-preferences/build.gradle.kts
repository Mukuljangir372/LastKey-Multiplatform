plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.preferences"
}
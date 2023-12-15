plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common:domain"))
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.feature.home.domain"
}

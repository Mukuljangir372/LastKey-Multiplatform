plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common:domain"))
                implementation(libs.koin)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.feature.credential.domain"
}

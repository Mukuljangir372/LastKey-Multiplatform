plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core-domain"))
                implementation(libs.koin)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.feature.credential.domain"
}

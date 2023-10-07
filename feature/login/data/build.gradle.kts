plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core-data"))
                implementation(project(":core-domain"))
                implementation(project(":feature:login:domain"))

                implementation(libs.koin)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.serializaton)
                implementation(libs.kotlinx.datetime)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.feature.login.data"
}

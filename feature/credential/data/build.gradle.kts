plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core-data"))
                implementation(project(":core-domain"))
                implementation(project(":core-network"))
                implementation(project(":core-database"))
                implementation(project(":feature:credential:domain"))

                implementation(libs.koin)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.serializaton)
                implementation(libs.kotlinx.datetime)
                implementation(libs.store)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.feature.credential.data"
}

plugins {
    id("com.mu.lastkey.kmm")
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlin.serialization)
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core-domain"))
                implementation(project(":core-database"))
                implementation(project(":core-network"))
                implementation(project(":core-utils"))
                implementation(libs.koin)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.serializaton)
                implementation(libs.kotlinx.datetime)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.koin.android)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
        }
    }
}
sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.mu.lastkey.core.data")
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.data"
}

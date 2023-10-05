plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core-utils"))
                api(libs.androidx.datastore.core)
                api(libs.androidx.datastore.okio)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kodein)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
        }
        val iosMain by getting {
            dependsOn(commonMain)
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.preferences"
}

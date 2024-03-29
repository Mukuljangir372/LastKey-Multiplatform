plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}
dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}
gradlePlugin {
    plugins {
        fun createPlugin(id: String, className: String) {
            plugins.create(id) {
                this.id = id
                implementationClass = className
            }
        }
        createPlugin(
            id = "com.mu.lastkey.kmm",
            className = "com.mu.lastkey.build.logic.convention.KMMConventionPlugin"
        )
    }
}
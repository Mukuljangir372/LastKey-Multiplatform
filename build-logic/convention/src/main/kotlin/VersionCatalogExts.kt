import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension

internal fun Project.version(key: String) = extensions
    .getByType(VersionCatalogsExtension::class.java)
    .named("libs")
    .findVersion(key)
    .get()
    .requiredVersion

internal fun Project.versionInt(key: String) = version(key).toInt()

internal val Project.ANDROID_COMPILE_SDK_VERSION get() = versionInt("androidCompileSdk")
internal val Project.ANDROID_MIN_SDK_VERSION get() = versionInt("androidMinSdk")

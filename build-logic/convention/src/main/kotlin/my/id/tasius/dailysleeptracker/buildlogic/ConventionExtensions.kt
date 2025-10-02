package my.id.tasius.dailysleeptracker.buildlogic

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(frameworkName: String = name) {
    extensions.configure<KotlinMultiplatformExtension> {
        applyDefaultHierarchyTemplate()

        androidTarget {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }

        listOf(
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = frameworkName
                isStatic = true
            }
        }
    }
}

internal fun CommonExtension<*, *, *, *, *, *>.configureAndroidCommonOptions() {
    compileOptions.sourceCompatibility = JavaVersion.VERSION_11
    compileOptions.targetCompatibility = JavaVersion.VERSION_11
}

internal fun Project.configureAndroidLibrary() {
    extensions.configure<LibraryExtension> {
        val compileSdkVersion = versionInt("android-compileSdk")
        val minSdkVersion = versionInt("android-minSdk")

        compileSdk = compileSdkVersion
        defaultConfig {
            minSdk = minSdkVersion
        }

        configureAndroidCommonOptions()
    }
}

internal fun Project.versionInt(key: String): Int =
    libs.findVersion(key).orElseThrow {
        IllegalArgumentException("Version '$key' is not defined in the version catalog.")
    }.requiredVersion.toInt()

@OptIn(ExperimentalComposeLibrary::class)
internal fun DependencyHandlerScope.addComposeUiDependencies(
    composeDependencies: ComposePlugin.Dependencies,
    libs: VersionCatalog,
) {
    commonMainImplementation(composeDependencies.runtime)
    commonMainImplementation(composeDependencies.foundation)
    commonMainImplementation(composeDependencies.material3)
    commonMainImplementation(composeDependencies.ui)
    commonMainImplementation(composeDependencies.components.resources)
    commonMainImplementation(composeDependencies.components.uiToolingPreview)
    commonMainImplementation(libs.requireLibrary("androidx.lifecycle.viewmodelCompose"))
    commonMainImplementation(libs.requireLibrary("androidx.lifecycle.runtimeCompose"))
}

internal fun DependencyHandlerScope.addFeatureDependencies(libs: VersionCatalog) {
    commonMainImplementation(libs.requireLibrary("androidx.navigation.compose"))
}

internal fun DependencyHandlerScope.addKoinComposeDependencies(libs: VersionCatalog) {
    commonMainImplementation(libs.requireLibrary("koin.core"))
    commonMainImplementation(libs.requireLibrary("koin.androidx.compose"))
}

internal fun DependencyHandlerScope.addKoinCoreDependencies(libs: VersionCatalog) {
    commonMainImplementation(libs.requireLibrary("koin.core"))
}

internal fun DependencyHandlerScope.addImageLoadingDependencies(libs: VersionCatalog) {
    commonMainImplementation(libs.requireLibrary("coil.compose"))
    commonMainImplementation(libs.requireLibrary("coil.network.ktor3"))
}

internal fun DependencyHandlerScope.addNetworkingDependencies(libs: VersionCatalog) {
    commonMainImplementation(libs.requireLibrary("ktor.client.core"))
    commonMainImplementation(libs.requireLibrary("ktor.serialization.kotlinx.json"))
    commonMainImplementation(libs.requireLibrary("ktor.client.content.negotiation"))
    commonMainImplementation(libs.requireLibrary("ktor.client.mock"))
    commonMainImplementation(libs.requireLibrary("kotlinx.serialization.json"))
}

internal fun DependencyHandlerScope.addDataStoreDependencies(libs: VersionCatalog) {
    commonMainImplementation(libs.requireLibrary("androidx.datastore"))
    commonMainImplementation(libs.requireLibrary("androidx.datastore.preferences"))
}

internal fun DependencyHandlerScope.addKotlinTestDependencies(libs: VersionCatalog) {
    commonTestImplementation(libs.requireLibrary("kotlin.test"))
}

internal fun DependencyHandlerScope.addKtorPlatformDependencies(libs: VersionCatalog) {
    androidMainImplementation(libs.requireLibrary("ktor.client.okhttp"))
    iosMainImplementation(libs.requireLibrary("ktor.client.darwin"))
}

internal fun VersionCatalog.requireLibrary(alias: String): Provider<MinimalExternalModuleDependency> =
    findLibrary(alias).orElseThrow {
        IllegalArgumentException("Library '$alias' is not defined in the version catalog.")
    }

internal fun DependencyHandlerScope.commonMainImplementation(dependency: Any) {
    add("commonMainImplementation", dependency)
}

internal fun DependencyHandlerScope.androidMainImplementation(dependency: Any) {
    add("androidMainImplementation", dependency)
}

internal fun DependencyHandlerScope.iosMainImplementation(dependency: Any) {
    add("iosMainImplementation", dependency)
}

internal fun DependencyHandlerScope.commonTestImplementation(dependency: Any) {
    add("commonTestImplementation", dependency)
}

internal fun DependencyHandlerScope.debugImplementation(dependency: Any) {
    add("debugImplementation", dependency)
}

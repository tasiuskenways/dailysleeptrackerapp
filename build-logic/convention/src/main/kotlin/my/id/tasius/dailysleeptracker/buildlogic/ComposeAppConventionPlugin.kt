package my.id.tasius.dailysleeptracker.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.compose")
            apply("org.jetbrains.kotlin.plugin.compose")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        configureKotlinMultiplatform()
        configureAndroidApplication()
        configureComposeAppDependencies()
    }
}

private fun Project.configureKotlinMultiplatform() {
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
                baseName = "ComposeApp"
                isStatic = true
            }
        }
    }
}

private fun Project.configureAndroidApplication() {
    extensions.configure<ApplicationExtension> {
        val compileSdkVersion = versionInt("android-compileSdk")
        val minSdkVersion = versionInt("android-minSdk")
        val targetSdkVersion = versionInt("android-targetSdk")

        compileSdk = compileSdkVersion
        defaultConfig {
            minSdk = minSdkVersion
            targetSdk = targetSdkVersion
        }

        packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}

private fun Project.versionInt(key: String): Int =
    libs.findVersion(key).orElseThrow {
        IllegalArgumentException("Version '$key' is not defined in the version catalog.")
    }.requiredVersion.toInt()

private fun Project.configureComposeAppDependencies() {
    val composeExtension = extensions.getByName("compose") as ComposeExtension
    val composeDependencies = composeExtension.dependencies
    val libs = libs

    dependencies {
        addCoreDependencies(composeDependencies, libs)
        addFeatureDependencies(libs)
        addSupportingDependencies(libs)

        androidMainImplementation(composeDependencies.preview)
        androidMainImplementation(libs.requireLibrary("androidx.activity.compose"))
        androidMainImplementation(libs.requireLibrary("ktor.client.okhttp"))

        iosMainImplementation(libs.requireLibrary("ktor.client.darwin"))

        commonTestImplementation(libs.requireLibrary("kotlin.test"))

        debugImplementation(composeDependencies.uiTooling)
    }
}

@OptIn(ExperimentalComposeLibrary::class)
private fun DependencyHandlerScope.addCoreDependencies(
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
    commonMainImplementation(libs.requireLibrary("kotlinx.serialization.json"))
}

private fun DependencyHandlerScope.addFeatureDependencies(libs: VersionCatalog) {
    commonMainImplementation(libs.requireLibrary("koin.core"))
    commonMainImplementation(libs.requireLibrary("koin.androidx.compose"))
    commonMainImplementation(libs.requireLibrary("androidx.navigation.compose"))
}

private fun DependencyHandlerScope.addSupportingDependencies(libs: VersionCatalog) {
    commonMainImplementation(libs.requireLibrary("coil.compose"))
    commonMainImplementation(libs.requireLibrary("coil.network.ktor3"))
    commonMainImplementation(libs.requireLibrary("ktor.client.core"))
    commonMainImplementation(libs.requireLibrary("ktor.serialization.kotlinx.json"))
    commonMainImplementation(libs.requireLibrary("ktor.client.content.negotiation"))
    commonMainImplementation(libs.requireLibrary("ktor.client.mock"))
    commonMainImplementation(libs.requireLibrary("androidx.datastore"))
    commonMainImplementation(libs.requireLibrary("androidx.datastore.preferences"))
}

private fun VersionCatalog.requireLibrary(alias: String): Provider<MinimalExternalModuleDependency> =
    findLibrary(alias).orElseThrow {
        IllegalArgumentException("Library '$alias' is not defined in the version catalog.")
    }

private fun DependencyHandlerScope.commonMainImplementation(dependency: Any) {
    add("commonMainImplementation", dependency)
}

private fun DependencyHandlerScope.androidMainImplementation(dependency: Any) {
    add("androidMainImplementation", dependency)
}

private fun DependencyHandlerScope.iosMainImplementation(dependency: Any) {
    add("iosMainImplementation", dependency)
}

private fun DependencyHandlerScope.commonTestImplementation(dependency: Any) {
    add("commonTestImplementation", dependency)
}

private fun DependencyHandlerScope.debugImplementation(dependency: Any) {
    add("debugImplementation", dependency)
}

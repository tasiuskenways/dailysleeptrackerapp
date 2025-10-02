package my.id.tasius.dailysleeptracker.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.compose.ComposeExtension

class ComposeAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.compose")
            apply("org.jetbrains.kotlin.plugin.compose")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        configureKotlinMultiplatform(frameworkName = "ComposeApp")
        configureAndroidApplication()
        configureComposeAppDependencies()
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

        configureAndroidCommonOptions()
    }
}

private fun Project.configureComposeAppDependencies() {
    val composeExtension = extensions.getByName("compose") as ComposeExtension
    val composeDependencies = composeExtension.dependencies
    val libs = libs

    dependencies {
        addComposeUiDependencies(composeDependencies, libs)
        addFeatureDependencies(libs)
        addImageLoadingDependencies(libs)
        addNetworkingDependencies(libs)
        addDataStoreDependencies(libs)
        addKoinComposeDependencies(libs)

        androidMainImplementation(composeDependencies.preview)
        androidMainImplementation(libs.requireLibrary("androidx.activity.compose"))
        debugImplementation(composeDependencies.uiTooling)
        addKotlinTestDependencies(libs)
        addKtorPlatformDependencies(libs)
    }
}

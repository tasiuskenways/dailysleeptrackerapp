package my.id.tasius.dailysleeptracker.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CoreConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        configureKotlinMultiplatform(frameworkName = name)
        configureAndroidLibrary()
        configureCoreDependencies()
    }
}

private fun Project.configureCoreDependencies() {
    val libs = libs

    dependencies {
        addNetworkingDependencies(libs)
        addDataStoreDependencies(libs)
        addKoinCoreDependencies(libs)
        addKotlinTestDependencies(libs)
        addKtorPlatformDependencies(libs)
    }
}

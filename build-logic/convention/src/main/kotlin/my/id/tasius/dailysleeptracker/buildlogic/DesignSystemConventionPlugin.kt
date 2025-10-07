package my.id.tasius.dailysleeptracker.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.compose.ComposeExtension

class DesignSystemConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.compose")
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        configureKotlinMultiplatform(frameworkName = name)
        configureAndroidLibrary()
        configureDesignSystemDependencies()
    }
}

private fun Project.configureDesignSystemDependencies() {
    val composeExtension = extensions.getByName("compose") as ComposeExtension
    val composeDependencies = composeExtension.dependencies
    val libs = libs

    dependencies {
        addComposeUiDependencies(composeDependencies, libs)
        addKotlinTestDependencies(libs)
        debugImplementation(composeDependencies.uiTooling)
        androidMainImplementation(composeDependencies.preview)
    }
}

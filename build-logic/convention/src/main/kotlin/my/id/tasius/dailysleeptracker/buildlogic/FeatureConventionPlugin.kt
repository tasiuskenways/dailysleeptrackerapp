package my.id.tasius.dailysleeptracker.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.compose.ComposeExtension

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.compose")
            apply("org.jetbrains.kotlin.plugin.compose")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        configureKotlinMultiplatform(frameworkName = name)
        configureAndroidLibrary()
        configureFeatureDependencies()
    }
}

private fun Project.configureFeatureDependencies() {
    val composeExtension = extensions.getByName("compose") as ComposeExtension
    val composeDependencies = composeExtension.dependencies
    val libs = libs

    dependencies {
        addComposeUiDependencies(composeDependencies, libs)
        addFeatureDependencies(libs)
        addImageLoadingDependencies(libs)
        addKoinComposeDependencies(libs)
        addKotlinTestDependencies(libs)
        debugImplementation(composeDependencies.uiTooling)
        androidMainImplementation(composeDependencies.preview)
    }
}

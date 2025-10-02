plugins {
    alias(libs.plugins.dailysleeptracker.feature)
}

android {
    namespace = "my.id.tasius.dailysleeptracker.feature.splashscreen"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

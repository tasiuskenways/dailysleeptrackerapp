plugins {
    alias(libs.plugins.dailysleeptracker.feature)
}

android {
    namespace = "my.id.tasius.dailysleeptracker.feature.dashboard"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.network)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

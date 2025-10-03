plugins {
    alias(libs.plugins.dailysleeptracker.core)
}

android {
    namespace = "my.id.tasius.dailysleeptracker.core.navigation"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

plugins {
    alias(libs.plugins.dailysleeptracker.core)
}

android {
    namespace = "my.id.tasius.dailysleeptracker.core.common"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

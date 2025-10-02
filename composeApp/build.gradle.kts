plugins {
    alias(libs.plugins.dailysleeptracker.composeapp)
}

android {
    namespace = "my.id.tasius.dailysleeptracker"
    defaultConfig {
        applicationId = "my.id.tasius.dailysleeptracker"
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.network)
            implementation(projects.feature.splashscreen)
        }
    }
}

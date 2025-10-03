import io.kotzilla.gradle.ext.KotzillaKeyGeneration

plugins {
    alias(libs.plugins.dailysleeptracker.composeapp)
    alias(libs.plugins.kotzilla)
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
            implementation(projects.core.navigation)
            implementation(projects.feature.splashscreen)
            implementation(projects.feature.dashboard)
            implementation(libs.kotzilla.sdk)
        }
    }
}

kotzilla {
    versionName = "1.0"
    keyGeneration = KotzillaKeyGeneration.COMPOSE
    composeInstrumentation = true
}

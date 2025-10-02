import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "my.id.tasius.dailysleeptracker.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("composeAppConvention") {
            id = "dailysleeptracker.composeapp"
            implementationClass = "my.id.tasius.dailysleeptracker.buildlogic.ComposeAppConventionPlugin"
        }
        register("featureConvention") {
            id = "dailysleeptracker.feature"
            implementationClass = "my.id.tasius.dailysleeptracker.buildlogic.FeatureConventionPlugin"
        }
        register("coreConvention") {
            id = "dailysleeptracker.core"
            implementationClass = "my.id.tasius.dailysleeptracker.buildlogic.CoreConventionPlugin"
        }
    }
}

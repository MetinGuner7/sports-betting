import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.sports.betting.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.easylauncher.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "sports.android.application.compose"
            implementationClass = "com.sports.betting.build_logic.convention.AndroidComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "sports.android.application"
            implementationClass = "com.sports.betting.build_logic.convention.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = "sports.android.application.jacoco"
            implementationClass = "com.sports.betting.build_logic.convention.AndroidApplicationJacocoConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "sports.android.library.compose"
            implementationClass = "com.sports.betting.build_logic.convention.AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "sports.android.library"
            implementationClass = "com.sports.betting.build_logic.convention.AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "sports.android.feature"
            implementationClass = "com.sports.betting.build_logic.convention.AndroidFeatureConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = "sports.android.library.jacoco"
            implementationClass = "com.sports.betting.build_logic.convention.AndroidLibraryJacocoConventionPlugin"
        }
        register("androidTest") {
            id = "sports.android.test"
            implementationClass = "com.sports.betting.build_logic.convention.AndroidTestConventionPlugin"
        }
        register("androidHilt") {
            id = "sports.android.hilt"
            implementationClass = "com.sports.betting.build_logic.convention.AndroidHiltConventionPlugin"
        }
        register("jvmLibrary") {
            id = "sports.jvm.library"
            implementationClass = "com.sports.betting.build_logic.convention.JvmLibraryConventionPlugin"
        }
        register("firebase") {
            id = "sports.firebase"
            implementationClass = "com.sports.betting.build_logic.convention.FirebaseConventionPlugin"
        }
    }
}

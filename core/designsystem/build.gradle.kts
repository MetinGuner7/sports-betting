plugins {
    alias(libs.plugins.sports.android.library)
    alias(libs.plugins.sports.android.library.compose)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.sports.designsystem"
}

dependencies {
    api(libs.compose.foundation.layout)
    api(libs.compose.material.icons.extended)
    api(libs.compose.material3)
    api(libs.compose.runtime)
    api(libs.androidx.ui)
    api(libs.androidx.ui.util)
    api(libs.androidx.ui.graphics)
    api(projects.core.common)
    api(libs.timber)
    api(libs.coil.kt.svg)
    api(libs.coil.kt)
    api(libs.coil.kt.compose)
    api(libs.coil.network.okhttp)

    implementation(libs.androidx.core.ktx)
}

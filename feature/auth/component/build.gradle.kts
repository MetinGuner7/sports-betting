plugins {
    alias(libs.plugins.sports.android.feature)
    alias(libs.plugins.sports.android.library.compose)
}

android {
    namespace = "com.sports.auth.component"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(platform(libs.firebase.bom))
    api(libs.firebase.auth)
    implementation(libs.kotlinx.coroutines.play.services)
}

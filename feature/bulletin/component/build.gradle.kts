plugins {
    alias(libs.plugins.sports.android.feature)
    alias(libs.plugins.sports.android.library.compose)
}

android {
    namespace = "com.sports.bulletin.component"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

}

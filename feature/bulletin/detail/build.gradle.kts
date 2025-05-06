plugins {
    alias(libs.plugins.sports.android.feature)
    alias(libs.plugins.sports.android.library.compose)
}

android {
    namespace = "com.sports.bulletin.detail"

    defaultConfig { testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" }
}

dependencies {
    implementation(projects.feature.bulletin.component)
    implementation(projects.core.datastore)
    implementation(libs.androidx.activity.compose)
}

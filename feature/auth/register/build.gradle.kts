plugins {
    alias(libs.plugins.sports.android.feature)
    alias(libs.plugins.sports.android.library.compose)
}

android {
    namespace = "com.sports.auth.register"

    defaultConfig { testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" }
}

dependencies {
    implementation(projects.feature.auth.component)
    implementation(projects.core.datastore)
    implementation(libs.androidx.activity.compose)
}

plugins {
    alias(libs.plugins.sports.android.application)
    alias(libs.plugins.sports.android.application.compose)
    alias(libs.plugins.sports.android.hilt)

    id(libs.plugins.secrets.get().pluginId)
    alias(libs.plugins.google.services)
}

android {
    defaultConfig {
        applicationId = "com.sports.betting"
        versionCode = 1000
        versionName = "0.0.39"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }

    namespace = "com.sports.betting"
    buildFeatures { buildConfig = true }
}

dependencies {
    implementation(projects.core.analytics)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
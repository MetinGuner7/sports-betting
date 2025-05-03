package com.sports.betting.build_logic.convention

import com.android.build.gradle.LibraryExtension
import com.sports.betting.build_logic.convention.template.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("sports.android.library")
                apply("sports.android.hilt")
                apply(libs.findPlugin("serialization").get().get().pluginId)
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner =
                        "template.core.testing.NiaTestRunner"
                }
            }

            dependencies {
/*                add("implementation", project(":core:ui"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:resources"))
                add("implementation", project(":core:network"))
                add("implementation", project(":core:monitor"))
                add("implementation", project(":core:analytics"))*/

                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", libs.findLibrary("coil.kt.compose").get())

                add("implementation", libs.findLibrary("hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModel.compose").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
                add("implementation", libs.findLibrary("kotlinx.collections.immutable").get())
                add("implementation", libs.findLibrary("firebase.analytics").get())
            }
        }
    }
}

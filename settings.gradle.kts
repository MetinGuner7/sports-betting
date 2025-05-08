pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "sports-betting"
include(":app")
include(":core:common")
include(":core:analytics")
include(":core:designsystem")
include(":core:ui")
include(":core:monitor")
include(":feature:splash")
include(":core:datastore")
include(":core:datastore-proto")
include(":core:network")
include(":feature:bulletin:list")
include(":feature:bulletin:component")
include(":feature:bulletin:detail")
include(":feature:auth:login")
include(":feature:auth:register")
include(":feature:auth:component")
include(":feature:bulletin:basket")

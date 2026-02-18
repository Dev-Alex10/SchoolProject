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
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") // So we can use modules in gradle e.g:projects.feature
rootProject.name = "SchoolProject"
include(":app")
include(":core:data")
include(":core:database")
include(":core:designsystem")
include(":core:domain")
include(":core:presentation")
include(":feature:auth:presentation")
include(":feature:dashboard:presentation")
include(":feature:dashboard:domain")
include(":feature:dashboard:data")
include(":feature:profile:presentation")
include(":feature:settings:presentation")

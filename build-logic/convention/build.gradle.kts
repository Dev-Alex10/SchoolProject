import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}
group = "com.google.samples.apps.nowinandroid.buildlogic"
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}
dependencies{
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.gradleApiPlugin)
    compileOnly(libs.room.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}
gradlePlugin{
    plugins{
        register("androidApplication") {
            id = libs.plugins.myschoolproject.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.myschoolproject.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.myschoolproject.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.myschoolproject.feature.asProvider().get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidFeaturePresentation") {
            id = libs.plugins.myschoolproject.feature.presentation.get().pluginId
            implementationClass = "AndroidFeaturePresentationConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.myschoolproject.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("room") {
            id = libs.plugins.myschoolproject.room.get().pluginId
            implementationClass = "RoomConventionPlugin"
        }
    }
}

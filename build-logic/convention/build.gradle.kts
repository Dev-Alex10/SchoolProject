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
//    compileOnly(libs.android.tools.common)
    compileOnly(libs.ksp.gradlePlugin)
}
gradlePlugin{
    plugins{
        register("androidApplication") {
            id = libs.plugins.myschoolproject.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.myschoolproject.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.myschoolproject.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidFeaturePresentation") {
            id = libs.plugins.myschoolproject.android.feat.presentation.get().pluginId
            implementationClass = "AndroidFeaturePresentationConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.myschoolproject.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
    }
}

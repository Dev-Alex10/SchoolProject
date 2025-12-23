plugins {
    `kotlin-dsl`
}
group = "com.google.samples.apps.nowinandroid.buildlogic"
java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24
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
        register("androidLibrary") {
            id = libs.plugins.myschoolproject.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.myschoolproject.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.myschoolproject.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
    }
}

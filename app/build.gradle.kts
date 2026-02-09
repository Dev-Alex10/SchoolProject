plugins {
    alias(libs.plugins.myschoolproject.application)
    alias(libs.plugins.myschoolproject.hilt)
    alias(libs.plugins.google.services)
}

android {
    defaultConfig {
        applicationId = "my.schoolProject"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.feature.auth.presentation)
    implementation(projects.feature.auth.data)
    implementation(projects.feature.dashboard.presentation)

    implementation(libs.navigation.compose)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.test.manifest)
}
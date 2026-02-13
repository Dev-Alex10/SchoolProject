plugins {
    alias(libs.plugins.myschoolproject.feature.presentation)
}

android {
    namespace = "my.schoolproject.dashboard.presentation"
}

dependencies {
    implementation(projects.feature.dashboard.domain)

    implementation(libs.coil.compose)
    implementation(libs.coil.network)
}
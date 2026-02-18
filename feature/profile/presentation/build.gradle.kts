plugins {
    alias(libs.plugins.myschoolproject.feature.presentation)
}

android {
    namespace = "my.schoolproject.profile.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.coil.network)
}
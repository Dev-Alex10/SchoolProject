plugins {
    alias(libs.plugins.myschoolproject.android.feat.presentation)
}

android {
    namespace = "my.schoolproject.auth.presentation"
}

dependencies {
    implementation(projects.feature.auth.domain)

    implementation(libs.compose.ui)
}
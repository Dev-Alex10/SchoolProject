plugins {
    alias(libs.plugins.myschoolproject.android.feat.presentation)
}

android {
    namespace = "my.schoolproject.auth.presentation"
}

dependencies {
    implementation(libs.compose.ui)
}
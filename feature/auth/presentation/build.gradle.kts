plugins {
    alias(libs.plugins.myschoolproject.feat.presentation)
}

android {
    namespace = "my.schoolproject.auth.presentation"
}

dependencies {
    implementation(projects.feature.auth.domain)
}
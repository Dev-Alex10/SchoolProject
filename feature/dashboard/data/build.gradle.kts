plugins {
    alias(libs.plugins.myschoolproject.feature)
}

android {
    namespace = "my.schoolproject.dashboard.data"
}

dependencies {
    implementation(projects.feature.dashboard.domain)
    implementation(libs.retrofit)
    implementation(libs.gson)
}
plugins {
    alias(libs.plugins.myschoolproject.feature)
    alias(libs.plugins.myschoolproject.room)
}

android {
    namespace = "my.schoolproject.auth.database"
}

dependencies {
    implementation(projects.feature.auth.domain)
}
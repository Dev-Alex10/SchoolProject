plugins {
    alias(libs.plugins.myschoolproject.library)
    alias(libs.plugins.myschoolproject.room)
    alias(libs.plugins.myschoolproject.hilt)
}

android {
    namespace = "my.schoolproject.core.database"
}

dependencies {
    implementation(projects.core.domain)
}
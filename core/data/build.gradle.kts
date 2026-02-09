plugins {
    alias(libs.plugins.myschoolproject.library)
    alias(libs.plugins.myschoolproject.hilt)
}

android {
    namespace = "my.schoolproject.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
}

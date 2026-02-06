plugins {
    alias(libs.plugins.myschoolproject.feature)
}

android {
    namespace = "my.schoolproject.auth.data"
}

dependencies {
    implementation(projects.feature.auth.domain)
    implementation(projects.feature.auth.database)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
}

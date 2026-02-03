plugins {
    alias(libs.plugins.myschoolproject.android.feature)
}

android {
    namespace = "my.schoolproject.auth.data"
}

dependencies {
    implementation(projects.feature.auth.domain)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.room)
    implementation(libs.room.coroutines)
}

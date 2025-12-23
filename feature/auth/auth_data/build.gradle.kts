plugins {
    alias(libs.plugins.myschoolproject.android.feature)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "my.schoolproject.auth_data"
}

dependencies {
    implementation(project(":feature:auth:auth_domain"))

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.room)
    implementation(libs.room.coroutines)
}

plugins {
    alias(libs.plugins.myschoolproject.library)
}

android {
    namespace = "my.schoolproject.core.domain"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
plugins {
    alias(libs.plugins.myschoolproject.library)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "my.schoolproject.core.presentation"
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.kotlinx.coroutines.core)
}
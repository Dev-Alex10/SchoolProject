import com.android.build.api.dsl.LibraryExtension
import my.schoolProject.configureAndroidCompose
import my.schoolProject.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeaturePresentationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "myschoolproject.feature")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)

            dependencies {
                "implementation"(project(":core:presentation"))
                "implementation"(libs.findLibrary("hilt-navigation-compose").get())
                "implementation"(libs.findLibrary("compose-ui").get())

                "testImplementation"(libs.findLibrary("navigation-testing").get())

                "androidTestImplementation"(
                    libs.findLibrary("lifecycle-runtimeTesting").get(),
                )
            }
        }
    }
}
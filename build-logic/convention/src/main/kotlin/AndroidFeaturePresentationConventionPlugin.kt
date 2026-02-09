
import my.schoolProject.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePresentationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "myschoolproject.library.compose")
            apply(plugin = "myschoolproject.feature")


            dependencies {
                "implementation"(project(":core:presentation"))
                "implementation"(project(":core:designsystem"))

                "implementation"(libs.findLibrary("hilt-navigation-compose").get())
                "testImplementation"(libs.findLibrary("navigation-testing").get())
            }
        }
    }
}
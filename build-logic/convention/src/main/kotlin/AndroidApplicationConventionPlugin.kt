import com.android.build.api.dsl.ApplicationExtension
import my.schoolProject.configureAndroidCompose
import my.schoolProject.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            extensions.configure<ApplicationExtension> {
                namespace = "my.schoolProject"
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                testOptions.animationsDisabled = true
            }
        }
    }
}
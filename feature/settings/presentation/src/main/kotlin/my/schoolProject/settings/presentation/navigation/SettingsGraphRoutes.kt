package my.schoolProject.settings.presentation.navigation

import kotlinx.serialization.Serializable
import my.schoolproject.core.presentation.navigation.Route

interface SettingsGraphRoutes: Route {
    @Serializable
    data object Graph: SettingsGraphRoutes
    @Serializable
    data object Settings: SettingsGraphRoutes
}
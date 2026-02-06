package my.schoolproject.dashboard.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface DashboardGraphRoutes {
    @Serializable
    data object Graph : DashboardGraphRoutes

    @Serializable
    data object Home : DashboardGraphRoutes

    @Serializable
    data object Settings : DashboardGraphRoutes

}
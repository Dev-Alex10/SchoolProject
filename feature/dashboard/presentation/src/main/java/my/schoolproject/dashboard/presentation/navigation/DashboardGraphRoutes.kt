package my.schoolproject.dashboard.presentation.navigation

import kotlinx.serialization.Serializable
import my.schoolproject.core.presentation.navigation.Route

sealed interface DashboardGraphRoutes : Route {
    @Serializable
    data object Graph : DashboardGraphRoutes

    @Serializable
    data object Home : DashboardGraphRoutes

    @Serializable
    data object Details : DashboardGraphRoutes
}
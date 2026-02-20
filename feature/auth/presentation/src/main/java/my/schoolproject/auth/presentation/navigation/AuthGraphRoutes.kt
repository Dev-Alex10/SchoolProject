package my.schoolproject.auth.presentation.navigation

import kotlinx.serialization.Serializable
import my.schoolproject.core.presentation.navigation.Route

sealed interface AuthGraphRoutes: Route {
    @Serializable
    data object Graph : AuthGraphRoutes

    @Serializable
    data object Login : AuthGraphRoutes

    @Serializable
    data object Register : AuthGraphRoutes
}
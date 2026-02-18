package my.schoolproject.profile.presentation.navigation

import kotlinx.serialization.Serializable
import my.schoolproject.core.presentation.navigation.Route

sealed interface ProfileGraphRoutes : Route {
    @Serializable
    data object Graph : ProfileGraphRoutes

    @Serializable
    data object Profile : ProfileGraphRoutes

    @Serializable
    data object ProfileEdit : ProfileGraphRoutes
}
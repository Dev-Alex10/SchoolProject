package my.schoolproject.dashboard.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import my.schoolproject.dashboard.presentation.DashboardRoot
import my.schoolproject.dashboard.presentation.SettingsScreen

fun NavGraphBuilder.dashboardGraph(
    modifier: Modifier,
    onLogout: () -> Unit
) {

    navigation<DashboardGraphRoutes.Graph>(
        startDestination = DashboardGraphRoutes.Home,
    ) {
        composable<DashboardGraphRoutes.Home> {
            DashboardRoot(
                modifier = modifier,
                onDetailsClick = {},
                onLogout = onLogout,
            )
        }
        composable<DashboardGraphRoutes.Settings> {
            SettingsScreen()
        }
    }
}
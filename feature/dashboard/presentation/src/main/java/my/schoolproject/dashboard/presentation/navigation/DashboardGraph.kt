package my.schoolproject.dashboard.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import my.schoolproject.dashboard.presentation.DashboardScreen
import my.schoolproject.dashboard.presentation.DashboardViewModel
import my.schoolproject.dashboard.presentation.SettingsScreen

fun NavGraphBuilder.dashboardGraph(
    modifier: Modifier,
    onLogout: () -> Unit
) {

    navigation<DashboardGraphRoutes.Graph>(
        startDestination = DashboardGraphRoutes.Home,
    ) {
        composable<DashboardGraphRoutes.Home> {
            val viewModel = hiltViewModel<DashboardViewModel>()
            DashboardScreen(
                modifier = modifier,
                onLogout = onLogout,
                onFirebaseLogout = viewModel::logout
            )
        }
        composable<DashboardGraphRoutes.Settings> {
            SettingsScreen()
        }
    }
}
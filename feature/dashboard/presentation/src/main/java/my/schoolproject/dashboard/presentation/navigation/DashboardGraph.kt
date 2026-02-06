package my.schoolproject.dashboard.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import my.schoolproject.dashboard.presentation.DashboardScreen

fun NavGraphBuilder.dashboardGraph(
    navController: NavController,
) {

    navigation<DashboardGraphRoutes.Graph>(
        startDestination = DashboardGraphRoutes.Home,
    ) {
        composable<DashboardGraphRoutes.Home> {
            DashboardScreen(topAppBarTitle = DashboardGraphRoutes.Home.javaClass.simpleName)
        }
    }
}
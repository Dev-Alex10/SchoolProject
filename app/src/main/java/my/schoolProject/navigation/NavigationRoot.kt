package my.schoolProject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import my.schoolProject.settings.presentation.navigation.settingsGraph
import my.schoolproject.auth.presentation.navigation.AuthGraphRoutes
import my.schoolproject.auth.presentation.navigation.authGraph
import my.schoolproject.dashboard.presentation.navigation.DashboardGraphRoutes
import my.schoolproject.dashboard.presentation.navigation.dashboardGraph
import my.schoolproject.profile.presentation.navigation.profileGraph

@Composable
fun NavigationRoot(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = {
            authGraph(
                navController = navController,
                onSuccess = {
                    navController.navigate(DashboardGraphRoutes.Graph) {
                        popUpTo(AuthGraphRoutes.Graph) {
                            inclusive = true
                        }
                    }
                },
            )
            dashboardGraph(
                modifier = modifier,
                navController = navController,
                onLogout = {
                    navController.navigate(AuthGraphRoutes.Graph) {
                        popUpTo(DashboardGraphRoutes.Graph) {
                            inclusive = true
                        }
                    }
                })
            profileGraph(
                modifier = modifier,
                navController = navController
            )
            settingsGraph(
                modifier = modifier,
            )
        }
    )
}
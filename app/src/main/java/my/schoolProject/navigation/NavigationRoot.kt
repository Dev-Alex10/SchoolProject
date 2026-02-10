package my.schoolProject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import my.schoolproject.auth.presentation.navigation.AuthGraphRoutes
import my.schoolproject.auth.presentation.navigation.authGraph
import my.schoolproject.dashboard.presentation.navigation.DashboardGraphRoutes
import my.schoolproject.dashboard.presentation.navigation.dashboardGraph

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
                onLogout = {
                    navController.navigate(AuthGraphRoutes.Graph) {
                        popUpTo(DashboardGraphRoutes.Graph) {
                            inclusive = true
                        }
                    }
                })
        }
    )
}
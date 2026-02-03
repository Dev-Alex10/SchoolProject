package my.schoolProject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import my.schoolproject.auth.presentation.navigation.AuthGraphRoutes
import my.schoolproject.auth.presentation.navigation.authGraph

@Composable
fun NavigationRoot(
    navController: NavHostController,
    startDestination: Any
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(navController) {
//            navController.navigate(LessonGraphRoutes.Graph) {
//                popUpTo(AuthGraphRoutes.Graph) {
//                    inclusive = true
//                }
//            }
        }
    }
}
package my.schoolProject.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import my.schoolProject.navigation.NavigationRoot
import my.schoolProject.ui.theme.MyApplicationTheme
import my.schoolproject.auth.presentation.navigation.AuthGraphRoutes

@Composable
fun App() {
    val navController = rememberNavController()

    MyApplicationTheme {
        NavigationRoot(
            navController,
            startDestination = AuthGraphRoutes.Graph
        )
    }
}

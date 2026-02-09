package my.schoolProject.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import my.schoolProject.navigation.NavigationRoot
import my.schoolproject.auth.presentation.navigation.AuthGraphRoutes
import my.schoolproject.core.designsystem.ui.theme.MyApplicationTheme
import my.schoolproject.dashboard.presentation.navigation.DashboardGraphRoutes

@Composable
fun App() {
    val navController = rememberNavController()
    val startDestination = if (Firebase.auth.currentUser != null) {
        DashboardGraphRoutes.Graph
    } else {
        AuthGraphRoutes.Graph
    }

    MyApplicationTheme {
        NavigationRoot(
            navController,
            startDestination = startDestination
        )
    }
}

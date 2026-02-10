@file:OptIn(ExperimentalMaterial3Api::class)

package my.schoolProject.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import my.schoolProject.components.BottomNavigationBar
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "Home"

    /**
     * The Scaffold needs to be on root because when changing from home to settings, if we had each with a scaffold, the state would be different
     * */
    val isDashBoardRoute = currentRoute.contains(DashboardGraphRoutes::class.java.simpleName)

    MyApplicationTheme {
        Scaffold(
            topBar = {
                if (isDashBoardRoute) {
                    CenterAlignedTopAppBar(
                        title = { Text(currentRoute.split(".").last()) }
                    )
                }
            },
            bottomBar = {
                if (isDashBoardRoute) {
                    BottomNavigationBar(navigate = { navController.navigate(it) })
                }
            }
        ) { paddingValues ->
            NavigationRoot(
                navController,
                startDestination = startDestination,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

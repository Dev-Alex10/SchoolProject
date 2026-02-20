@file:OptIn(ExperimentalMaterial3Api::class)

package my.schoolProject.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import my.schoolProject.components.BottomNavigationBar
import my.schoolProject.navigation.NavigationRoot
import my.schoolproject.auth.presentation.navigation.AuthGraphRoutes
import my.schoolproject.core.designsystem.ui.theme.MyApplicationTheme
import my.schoolproject.core.designsystem.ui.topbar.TopBarNavigationIcon
import my.schoolproject.core.presentation.util.LocalSnackbarHostState
import my.schoolproject.dashboard.presentation.navigation.DashboardGraphRoutes
import my.schoolproject.profile.presentation.navigation.ProfileGraphRoutes

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
    val snackbarHostState = remember { SnackbarHostState() }

    /**
     * The Scaffold needs to be on root because when changing from home to settings, if we had each with a scaffold, the state would be different
     * */
    val isAuthRoute = currentRoute.contains(AuthGraphRoutes::class.java.simpleName)


    MyApplicationTheme {
        CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                topBar = {
                    if (!isAuthRoute) {
                        CenterAlignedTopAppBar(
                            title = { Text(currentRoute.split(".").last()) },
                            navigationIcon = {
                                if (currentRoute.contains(ProfileGraphRoutes.ProfileEdit::class.java.simpleName)) {
                                    TopBarNavigationIcon(onBackClick = { navController.popBackStack() })
                                }
                            }
                        )
                    }
                },
                bottomBar = {
                    if (!isAuthRoute) {
                        BottomNavigationBar(
                            currentRoute = currentRoute,
                            navigate = {
                                navController.navigate(it) {
                                    popUpTo(DashboardGraphRoutes.Home::class) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                    }
                }
            ) { paddingValues ->
                NavigationRoot(
                    navController = navController,
                    startDestination = startDestination,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package my.schoolProject.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import my.schoolProject.navigation.NavigationRoot
import my.schoolproject.auth.presentation.navigation.AuthGraphRoutes
import my.schoolproject.core.designsystem.ui.theme.MyApplicationTheme
import my.schoolproject.dashboard.presentation.R.drawable
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
    MyApplicationTheme {
        Scaffold(
            topBar = {
                if (currentRoute.contains(DashboardGraphRoutes::class.java.simpleName)) {
                    CenterAlignedTopAppBar(
                        title = { Text(currentRoute.split(".").last()) }
                    )
                }
            },
            bottomBar = {
                if (currentRoute.contains(DashboardGraphRoutes::class.java.simpleName)) {
                    NavigationBar {
                        var selectedItem by rememberSaveable { mutableIntStateOf(0) }
                        val items = listOf(DashboardGraphRoutes.Home, DashboardGraphRoutes.Settings)
                        val icons = listOf(
                            drawable.feature_dashboard_presentation_home,
                            drawable.feature_dashboard_presentation_settings
                        )

                        items.forEachIndexed { index, item ->
                            val simpleName = item.javaClass.simpleName
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painterResource(id = icons[index]),
                                        contentDescription = simpleName
                                    )
                                },
                                selected = selectedItem == index,
                                onClick = {
                                    navController.navigate(item)
                                    selectedItem = index
                                },
                                label = { Text(simpleName) }
                            )
                        }
                    }
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

package my.schoolProject.nav.graph

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import my.schoolProject.nav.destinations.*

@Composable
fun BottomBarNavHost(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute != Login.route && currentRoute != Register.route) {
        BottomNavigation {
            val items = listOf(Classroom, Profile, Lesson)
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.route
                        )
                    },
                    label = { Text(screen.route) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigateSingleTopTo(screen.route)
                    })
            }
        }
    }
}

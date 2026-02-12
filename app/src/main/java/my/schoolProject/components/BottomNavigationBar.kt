package my.schoolProject.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import my.schoolproject.dashboard.presentation.R.drawable
import my.schoolproject.dashboard.presentation.navigation.DashboardGraphRoutes

@Composable
fun BottomNavigationBar(
    navigate: (DashboardGraphRoutes) -> Unit,
    currentRoute: String
) {
    NavigationBar {
        val items = listOf(DashboardGraphRoutes.Home, DashboardGraphRoutes.Settings)
        val icons = listOf(
            drawable.feature_dashboard_presentation_home,
            drawable.feature_dashboard_presentation_settings
        )

        items.forEachIndexed { index, item ->
            val simpleName = item.javaClass.simpleName
            val isSelected = currentRoute.contains(simpleName)
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = icons[index]),
                        contentDescription = simpleName
                    )
                },
                selected = isSelected,
                onClick = {
                    navigate(item)
                },
                label = { Text(simpleName) }
            )
        }
    }
}
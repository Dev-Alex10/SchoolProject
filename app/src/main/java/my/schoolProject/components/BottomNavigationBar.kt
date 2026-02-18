package my.schoolProject.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import my.schoolProject.R.drawable
import my.schoolProject.settings.presentation.navigation.SettingsGraphRoutes
import my.schoolproject.core.presentation.navigation.Route
import my.schoolproject.dashboard.presentation.navigation.DashboardGraphRoutes
import my.schoolproject.profile.presentation.navigation.ProfileGraphRoutes

@Composable
fun BottomNavigationBar(
    navigate: (Route) -> Unit,
    currentRoute: String
) {
    NavigationBar {
        val items = listOf(
            DashboardGraphRoutes.Home,
            ProfileGraphRoutes.Profile,
            SettingsGraphRoutes.Settings
        )
        val icons = listOf(
            drawable.presentation_home,
            drawable.feature_profile_presentation_profile_icon,
            drawable.presentation_settings
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
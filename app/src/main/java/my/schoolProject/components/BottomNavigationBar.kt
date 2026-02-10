package my.schoolProject.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import my.schoolproject.dashboard.presentation.R.drawable
import my.schoolproject.dashboard.presentation.navigation.DashboardGraphRoutes

@Composable
fun BottomNavigationBar(navigate: (DashboardGraphRoutes) -> Unit) {
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
                    navigate(item)
                    selectedItem = index
                },
                label = { Text(simpleName) }
            )
        }
    }
}